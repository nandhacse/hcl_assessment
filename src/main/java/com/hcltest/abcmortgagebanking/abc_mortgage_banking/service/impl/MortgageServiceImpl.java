package com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.impl;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.ServiceException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Mortgage;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.request.MortgageRequst;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository.MortgageRepository;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.MortgageService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.logging.Logger;


@Service
public class MortgageServiceImpl implements MortgageService {
    private static final Logger LOGGER = Logger.getLogger(MortgageServiceImpl.class.getName());
    private final  MortgageRepository mortgageRepository;

    public MortgageServiceImpl(MortgageRepository mortgageRepository) {
        this.mortgageRepository = mortgageRepository;
    }

    /**
     * This method is to create a new mortgage to the system
     * @param mortgage
     * @return
     * @throws ServiceException
     */
    @Override
    public String save(Mortgage mortgage) throws ServiceException {
        LOGGER.info("create mortgage started with request {0}:: "+ mortgage);
       try{
           Mortgage mortgage1 = mortgageRepository.save(mortgage);
           LOGGER.info("create mortgage is success");
           return "Success message upon creating mortgage account.";
       } catch (Exception e) {
           LOGGER.info("create mortgage started with request {0}:: "+ mortgage);
           throw new ServiceException(e.getMessage());
       }
    }


    /**
     * This method is used to login to the system using mortgage account number and password
     * @param mortgageRequst
     * @return
     * @throws ServiceException
     */
    @Override
    public String loginUsingMortgage(MortgageRequst mortgageRequst) throws ServiceException {
        LOGGER.info("login using mortgage started with request  {0}:: "+ mortgageRequst);
        try{
            Mortgage mortgage1 = mortgageRepository.findByMortgageAccountNumberAndPassword(mortgageRequst.getMortgageNumber(), mortgageRequst.getPassword());
            LOGGER.info("login using mortgage is success ");
            return Objects.nonNull(mortgage1) ? "Mortgage account fetched successfully."
                    : "Failed to get the mortgage account.";
        } catch (Exception e) {
            LOGGER.info("Failed to login with mortgage for the mortgage Account Number {0} "+ mortgageRequst.getMortgageNumber());
            throw new ServiceException(e.getMessage());
        }
    }
}
