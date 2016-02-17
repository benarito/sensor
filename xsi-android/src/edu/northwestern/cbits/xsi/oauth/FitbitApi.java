package edu.northwestern.cbits.xsi.oauth;

import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.DefaultApi10a;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import android.net.Uri;

import edu.northwestern.cbits.xsi.XSI;

public class FitbitApi extends DefaultApi10a 
{
	public static final String CONSUMER_KEY = "fitbit_consumer_key";
	public static final String CONSUMER_SECRET = "fitbit_consumer_secret";
	public static final String USER_SECRET = "fitbit_user_secret";
	public static final String USER_TOKEN = "fitbit_user_token";

	public FitbitApi()
	{
		super();
	}
	
	public String getAccessTokenEndpoint() 
	{
		return "https://api.fitbit.com/oauth/access_token";
	}

	public String getAuthorizationUrl(Token token) 
	{
		return "https://www.fitbit.com/oauth/authenticate?oauth_token=" + token.getToken();
	}

	public String getRequestTokenEndpoint() 
	{
		return "https://api.fitbit.com/oauth/request_token";
	}
	
	public static JSONObject fetch(Uri uri)
	{
		try
		{
            Token accessToken = new Token(Keystore.get(FitbitApi.USER_TOKEN), Keystore.get(FitbitApi.USER_SECRET));

            final OAuthRequest request = new OAuthRequest(Verb.GET, uri.toString());
            request.addHeader("User-Agent", XSI.getUserAgent());

            ServiceBuilder builder = new ServiceBuilder();
            builder = builder.provider(FitbitApi.class);
            builder = builder.apiKey(Keystore.get(FitbitApi.CONSUMER_KEY));
            builder = builder.apiSecret(Keystore.get(FitbitApi.CONSUMER_SECRET));

            final OAuthService service = builder.build();

            service.signRequest(accessToken, request);

            Response response = request.send();

			return new JSONObject(response.getBody());
		} 
		catch (JSONException | OAuthException e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
