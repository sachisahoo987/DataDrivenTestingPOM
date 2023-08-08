package com.zoho.base.pages;

import com.zoho.web.WebConnector;

public interface ZohoNormalPage {
	// ZOHO based normal
	ZohoPage gotoHomePage();
	ZohoPage gotoEnterUsernamePage();
    void goToRegisterPage();
    ZohoPage submitUsername(String userid);
    ZohoPage submitPassword(String userid);
    WebConnector validator(boolean flagAssertionType);
}
