package io.steamreviewbot.domain.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("steamid")
    @Expose
    private String steamid;
    @SerializedName("communityvisibilitystate")
    @Expose
    private Integer communityvisibilitystate;
    @SerializedName("profilestate")
    @Expose
    private Integer profilestate;
    @SerializedName("personaname")
    @Expose
    private String personaname;
    @SerializedName("commentpermission")
    @Expose
    private Integer commentpermission;
    @SerializedName("profileurl")
    @Expose
    private String profileurl;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("avatarmedium")
    @Expose
    private String avatarmedium;
    @SerializedName("avatarfull")
    @Expose
    private String avatarfull;
    @SerializedName("lastlogoff")
    @Expose
    private Integer lastlogoff;
    @SerializedName("personastate")
    @Expose
    private Integer personastate;
    @SerializedName("primaryclanid")
    @Expose
    private String primaryclanid;
    @SerializedName("timecreated")
    @Expose
    private Integer timecreated;
    @SerializedName("personastateflags")
    @Expose
    private Integer personastateflags;
    @SerializedName("loccountrycode")
    @Expose
    private String loccountrycode;
    @SerializedName("locstatecode")
    @Expose
    private String locstatecode;
    @SerializedName("loccityid")
    @Expose
    private Integer loccityid;

    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    public Integer getCommunityvisibilitystate() {
        return communityvisibilitystate;
    }

    public void setCommunityvisibilitystate(Integer communityvisibilitystate) {
        this.communityvisibilitystate = communityvisibilitystate;
    }

    public Integer getProfilestate() {
        return profilestate;
    }

    public void setProfilestate(Integer profilestate) {
        this.profilestate = profilestate;
    }

    public String getPersonaname() {
        return personaname;
    }

    public void setPersonaname(String personaname) {
        this.personaname = personaname;
    }

    public Integer getCommentpermission() {
        return commentpermission;
    }

    public void setCommentpermission(Integer commentpermission) {
        this.commentpermission = commentpermission;
    }

    public String getProfileurl() {
        return profileurl;
    }

    public void setProfileurl(String profileurl) {
        this.profileurl = profileurl;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarmedium() {
        return avatarmedium;
    }

    public void setAvatarmedium(String avatarmedium) {
        this.avatarmedium = avatarmedium;
    }

    public String getAvatarfull() {
        return avatarfull;
    }

    public void setAvatarfull(String avatarfull) {
        this.avatarfull = avatarfull;
    }

    public Integer getLastlogoff() {
        return lastlogoff;
    }

    public void setLastlogoff(Integer lastlogoff) {
        this.lastlogoff = lastlogoff;
    }

    public Integer getPersonastate() {
        return personastate;
    }

    public void setPersonastate(Integer personastate) {
        this.personastate = personastate;
    }

    public String getPrimaryclanid() {
        return primaryclanid;
    }

    public void setPrimaryclanid(String primaryclanid) {
        this.primaryclanid = primaryclanid;
    }

    public Integer getTimecreated() {
        return timecreated;
    }

    public void setTimecreated(Integer timecreated) {
        this.timecreated = timecreated;
    }

    public Integer getPersonastateflags() {
        return personastateflags;
    }

    public void setPersonastateflags(Integer personastateflags) {
        this.personastateflags = personastateflags;
    }

    public String getLoccountrycode() {
        return loccountrycode;
    }

    public void setLoccountrycode(String loccountrycode) {
        this.loccountrycode = loccountrycode;
    }

    public String getLocstatecode() {
        return locstatecode;
    }

    public void setLocstatecode(String locstatecode) {
        this.locstatecode = locstatecode;
    }

    public Integer getLoccityid() {
        return loccityid;
    }

    public void setLoccityid(Integer loccityid) {
        this.loccityid = loccityid;
    }

}
