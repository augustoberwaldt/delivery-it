package com.deliver.deliver.service;

import com.deliver.deliver.entity.Account;
import com.deliver.deliver.entity.AccountWrapper;
import com.deliver.deliver.enumerable.RateEnum;
import com.deliver.deliver.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public  AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    /**
     * getAll
     *
     * @return List<Account>
     */
    public List<AccountWrapper> getAccounts() {

        List<Account> listAccount =  this.accountRepository.findAll();
        List<AccountWrapper>  accountWrapperList = new ArrayList<AccountWrapper>();
         //TODO não ficou legal
        for (Account account : listAccount) {

            double valueCorrected = 0;

            RateEnum rateEnum =  RateEnum.valueOf("RATE"+ account.getRate());
            RateEnum swearEnum =  RateEnum.valueOf("SWEAR"+ account.getRate());


            int rate = Integer.parseInt(rateEnum.getValue());
            double swear = Double.parseDouble(swearEnum.getValue());

            double sumRate =  (account.getOriginalValue() * (rate/100));
            double sumSwear = (account.getOriginalValue() * (swear/100));
            sumSwear =  sumSwear  *  account.getDaysLate();
            valueCorrected =  account.getOriginalValue() +  sumRate + sumSwear;
            account.setCorrectedValue(valueCorrected);

            AccountWrapper accountWrapper = new  AccountWrapper();

            accountWrapper.setName(account.getName());
            accountWrapper.setCorrectedValue(valueCorrected);
            accountWrapper.setOriginalValue(account.getOriginalValue());
            accountWrapper.setDaysLate(account.getDaysLate());
            accountWrapper.setDtPayment(account.getDtPayment());
            accountWrapperList.add(accountWrapper);
        }


        return  accountWrapperList;
    }

    /**
     * Valida os dados de entrada
     *
     * @param account
     * @throws IllegalArgumentException
     */
    public void validateData(Account account)  throws IllegalArgumentException {

        if (account.getName() == null || account.getName().length() == 0) {
               throw new IllegalArgumentException("Nome e Obrigatorio.");
        }

        if (account.getDtMaturity() == null) {
            throw new IllegalArgumentException("Data de Vencimento e Obrigatorio.");
        }

        if (account.getDtPayment() == null) {
            throw new IllegalArgumentException("Data de Pagamento e Obrigatorio.");
        }

    }

    /**
     * save
     * @param Account account
     */
    public void save(Account account) {

        this.calculateRate(account);

        this.accountRepository.save(account);
    }

    /**
     * Calcula valor da taxa ser cobrado pelo atraso
     *
     * @param Account account
     */
    private  void calculateRate(Account account) {

        account.setDaysLate(this.daysBetween(account.getDtMaturity(), account.getDtPayment()));

        //TODO colocar base de dados
        if (account.getDaysLate() > 0) {
            if (account.getDaysLate() <= 3) {
                account.setRate(1);
            } else if (account.getDaysLate() > 3 && account.getDaysLate() <= 5) {
                account.setRate(2);
            } else {
                account.setRate(3);
            }

        }

    }

    /**
     * Calcula diferenca entre duas  datas
     *
     * @param Date a
     * @param Date b
     */
    public int daysBetween(Date a, Date b) {
        Calendar dInicial = Calendar.getInstance();
        dInicial.setTime(a); Calendar dFinal = Calendar.getInstance(); dFinal.setTime(b);
        int count = 0;
        while (dInicial.get(Calendar.DAY_OF_MONTH) != dFinal.get(Calendar.DAY_OF_MONTH)){
            dInicial.add(Calendar.DAY_OF_MONTH, 1);
            count ++;
        }

        return count;
    }

}
