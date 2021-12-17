package com.stock.services;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.uploader.UploadMetaData;


@Service
public class FlickrImpl {
	
	private Flickr flickr;

    private UploadMetaData uploadMetaData = new UploadMetaData();

    private String apiKey = "dd5cda708326dad067f1a9f2c1dec90f";

    private String apiSecret = "bc6897522b92ae91";


    private void connect(){
        flickr = new Flickr(apiKey, apiSecret, new REST());
        Auth auth = new Auth();
        auth.setPermission(Permission.DELETE);
        auth.setToken("72157720825532642-a30b5a8b53fe7c86");
        auth.setTokenSecret("2ee443338cc29949");
        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);
        flickr.setAuth(auth);
    }

    
    public String savePhoto(InputStream photo, String title) throws Exception {
        connect();
        uploadMetaData.setTitle(title);
        String photoId = flickr.getUploader().upload(photo, uploadMetaData);
        return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
    }
    
    
//    public void getFlickr() throws IOException, InterruptedException, ExecutionException, FlickrException {
//		Flickr flickr = new Flickr(apiKey, apiSecret, new REST());
//		
//		
//		OAuth10aService service = new ServiceBuilder(apiKey)
//				.apiSecret(apiSecret)
//				.build(FlickrApi.instance(FlickrPerm.DELETE));
//		
//		
//		final Scanner scanner = new Scanner(System.in);
//		
//		final OAuth1RequestToken request = service.getRequestToken();
//		
//		final String authUrl = service.getAuthorizationUrl(request);
//		
//		System.out.println(authUrl);
//		System.out.println("Paste it here >> ");
//		
//		final String authVerifier = scanner.nextLine();
//		
//		OAuth1AccessToken accessToken = service.getAccessToken(request, authVerifier);
//		
//		System.out.println(accessToken.getToken());
//		System.out.println(accessToken.getTokenSecret());
//		
//		Auth auth = flickr.getAuthInterface().checkToken(accessToken);
//		
//		System.out.println("----------------------------------------------------------");
//		System.out.println(auth.getToken());
//		System.out.println(auth.getTokenSecret());
//		
//	}

}
