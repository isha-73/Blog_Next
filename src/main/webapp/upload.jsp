<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.*, java.util.UUID" %>

<%
String uploadDir = "path/to/your/upload/directory/"; // Specify the directory where you want to store uploaded files.
String bannerImage = null;
String uploadedImage = null;

if (request.getPart("bannerFile") != null) {
    Part bannerPart = request.getPart("bannerFile");
    String bannerFileName = UUID.randomUUID().toString() + "-" + bannerPart.getSubmittedFileName();
    bannerPart.write(uploadDir + bannerFileName);
    bannerImage = "path/to/your/upload/directory/" + bannerFileName;
}

if (request.getPart("imageFile") != null) {
    Part imagePart = request.getPart("imageFile");
    String imageFileName = UUID.randomUUID().toString() + "-" + imagePart.getSubmittedFileName();
    imagePart.write(uploadDir + imageFileName);
    uploadedImage = "path" + imageFileName; //path where the images will be stored
}

request.setAttribute("bannerImage", bannerImage);
request.setAttribute("uploadedImage", uploadedImage);
%>
