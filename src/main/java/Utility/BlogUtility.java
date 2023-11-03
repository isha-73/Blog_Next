package Utility;
import java.util.*;

public class BlogUtility {
	
   private String writtenBy;
   private String title;
   private String description;
   private String timeStamp;
   private int likes;
   private ArrayList<String> comments = new ArrayList<>();
   private int commentCount;
   
   
   
public String getWrittenBy() {
	return writtenBy;
}
public void setWrittenBy(String writtenBy) {
	this.writtenBy = writtenBy;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getTimeStamp() {
	return timeStamp;
}
public void setTimeStamp(String timeStamp) {
	this.timeStamp = timeStamp;
}
public int getLikes() {
	return likes;
}
public void setLikes(int likes) {
	this.likes = likes;
}
public ArrayList<String> getComments() {
	return comments;
}
public void setComments(ArrayList<String> comments) {
	this.comments = comments;
}
public int getCommentCount() {
	return commentCount;
}
public void setCommentCount(int commentCount) {
	this.commentCount = commentCount;
}
   
   
   
   
}
