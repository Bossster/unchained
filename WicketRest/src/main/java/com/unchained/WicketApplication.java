package com.unchained;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import com.unchained.web.HomePage;

public class WicketApplication extends WebApplication {
	
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	@Override
	public void init() {
		super.init();
	}
	
	@Override
	public RuntimeConfigurationType getConfigurationType() {
		return super.getConfigurationType();
	}
	
}
