package io.steamreviewbot.services;

import io.steamreviewbot.domain.PageStatistics;
import io.steamreviewbot.domain.Post;
import io.steamreviewbot.domain.PostWithDetailedReactions;
import io.steamreviewbot.domain.ReactionOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.steamreviewbot.repositories.PageStatisticsRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

@Service
public class PageStatisticsService {

	@Autowired
	private PageStatisticsRepository repo;

	@Autowired
	private PostService postService;

	@Autowired
	private FacebookService fbService;

	@Autowired
	private ImageService imgService;


	public void generateStatsPost() throws IOException {

		Date dateAux = repo.getLastStatsPage().getPostDate();

		List<Post> allPosts = postService.getPostsAfterDate(dateAux);


		PageStatistics stats = new PageStatistics();

		List<Integer> postListAuxId = new ArrayList<>();
		List<Post> postListAux = new ArrayList<>();
		List<PostWithDetailedReactions> mostReactedAux = new ArrayList<>();


		allPosts.forEach( post -> {
			String auxId;
			PostWithDetailedReactions p;
			boolean ignore = false;

			if (post.getPostId().startsWith("DUALITY_")){
				auxId = post.getPostId().replaceFirst("DUALITY_", "");
				if (auxId.startsWith("1")){
					auxId = auxId.replaceFirst("1_", "");
				} else {
					ignore = true;
				}
			} else auxId = post.getPostId();

			if (!ignore){
				p = fbService.getReactionsFromPost(auxId);


				stats.setGrrs(stats.getGrrs() + p.getReactionsAngry().getTotalCount().intValue());
				stats.setCares(stats.getCares() + p.getReactionsCare().getTotalCount().intValue());
				stats.setHahas(stats.getHahas() + p.getReactionsHaha().getTotalCount().intValue());
				stats.setLikes(stats.getLikes() + p.getReactionsLikes().getTotalCount().intValue());
				stats.setSads(stats.getSads() + p.getReactionsSad().getTotalCount().intValue());
				stats.setWows(stats.getWows() + p.getReactionsWow().getTotalCount().intValue());
				stats.setLoves(stats.getLoves() + p.getReactionsLove().getTotalCount().intValue());

				if (mostReactedAux.size() == 0) {
					mostReactedAux.add(p);
					postListAux.add(post);
					postListAuxId.add(post.getId());
				} else {
					int i = 0;
					int j = mostReactedAux.size();
					while(i<j && i!=-1) {
						if (mostReactedAux.get(i).getReactions().getTotalCount() <= p.getReactions().getTotalCount()){
							mostReactedAux.add(i ,p);
							postListAux.add(post);
							postListAuxId.add(i, post.getId());
							i = -2;
						}
						i++;
					}

					if (mostReactedAux.size() < 10){
						mostReactedAux.add(p);
						postListAux.add(post);
						postListAuxId.add(post.getId());
					}
				}


			}
		});
		List<PostWithDetailedReactions> mostReacted = mostReactedAux.subList(0, 10);
		stats.setPost_ids(postListAuxId.subList(0, 10));
		List<Post> postListFinal = postListAux.subList(0, 10);

		LocalDate date = dateAux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate now = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		String albumTitle = "Stats from " + now.format(
				DateTimeFormatter.ofLocalizedDate( FormatStyle.MEDIUM )
						.withLocale( Locale.US ));

		String desc = allPosts.size() + " reviews have been posted since " + date.format(
				DateTimeFormatter.ofLocalizedDate( FormatStyle.MEDIUM )
						.withLocale( Locale.US )) + ".";

		String albumId = fbService.createAlbum(albumTitle);

		fbService.publishOnAlbum(albumId, imgService.getInputStream(imgService.genStatisticsImage(ordenateReactions(stats)), "jpg"), "Cover", desc);

		postListFinal.forEach(a -> {
			try {
				fbService.publishOnAlbum(albumId, fbService.getImageUrlFromPost(a.getPostId()), a.getPostId(), "Original post: https://www.facebook.com/reviewpostbot/photos/102746451339651/" + a.getPostId());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});


		repo.save(stats);
		//imgService.copyInputStreamToFile(imgService.getInputStream(imgService.genStatisticsImage(ordenateReactions(stats)), "jpg"), "c:\\test\\test.jpg");

	}

	public List<ReactionOrder> ordenateReactions(PageStatistics stats){
		List<ReactionOrder> list = new ArrayList<>();
		list.add(new ReactionOrder(stats.getHahas(), "https://forbidden-bot.s3-sa-east-1.amazonaws.com/reactions/haha.png"));
		list.add(new ReactionOrder(stats.getCares(), "https://forbidden-bot.s3-sa-east-1.amazonaws.com/reactions/care.png"));
		list.add(new ReactionOrder(stats.getLikes(), "https://forbidden-bot.s3-sa-east-1.amazonaws.com/reactions/like.png"));
		list.add(new ReactionOrder(stats.getGrrs(), "https://forbidden-bot.s3-sa-east-1.amazonaws.com/reactions/grr.png"));
		list.add(new ReactionOrder(stats.getLoves(), "https://forbidden-bot.s3-sa-east-1.amazonaws.com/reactions/love.png"));
		list.add(new ReactionOrder(stats.getSads(), "https://forbidden-bot.s3-sa-east-1.amazonaws.com/reactions/sad.png"));
		list.add(new ReactionOrder(stats.getWows(), "https://forbidden-bot.s3-sa-east-1.amazonaws.com/reactions/wow.png"));

		boolean ordenated = false;
		int c;

		while (!ordenated){
			c = 0;
			for (int i = 0; i < 6; i++) {
				if (list.get(i+1).count > list.get(i).count){
					ReactionOrder auxM = list.get(i+1);
					ReactionOrder auxm = list.get(i);
					list.remove(auxM);
					list.remove(auxm);

					list.add(i, auxm);
					list.add(i, auxM);
					c++;
				}
			}
			if (c == 0) ordenated = true;
		}

		return list;
	}


}
