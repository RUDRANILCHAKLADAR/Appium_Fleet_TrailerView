package utils.model;

import com.google.gson.annotations.SerializedName;

public class UserToken{

	@SerializedName("scope")
	private String scope;

	@SerializedName("expiresOn")
	private String expiresOn;

	@SerializedName("refreshBy")
	private String refreshBy;

	@SerializedName("token")
	private String token;

	@SerializedName("refreshInSeconds")
	private int refreshInSeconds;

	public String getScope(){
		return scope;
	}

	public String getExpiresOn(){
		return expiresOn;
	}

	public String getRefreshBy(){
		return refreshBy;
	}

	public String getToken(){
		return token;
	}

	public int getRefreshInSeconds(){
		return refreshInSeconds;
	}
}