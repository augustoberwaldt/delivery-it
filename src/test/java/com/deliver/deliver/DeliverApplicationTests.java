package com.deliver.deliver;

import com.deliver.deliver.entity.Account;
import com.deliver.deliver.entity.AccountWrapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DeliverApplicationTests  extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getAccountList() throws Exception {
        String uri = "/v1/account/getAccounts";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        AccountWrapper[] accountWrapperList = super.mapFromJson(content, AccountWrapper[].class);
        assertTrue(accountWrapperList.length > 0);
    }

    @Test
    public void createAccount() throws Exception {
        String uri = "/v1/account/newAccount";
        Account account = new Account();

        account.setName("Augusto Marlon Berwaldt");
        account.setOriginalValue(100);
        account.setDtMaturity(new Date("2019-10-05"));
        account.setDtPayment(new Date("2019-10-15"));


        String inputJson = super.mapToJson(account);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Account is created successfully");
    }

}
