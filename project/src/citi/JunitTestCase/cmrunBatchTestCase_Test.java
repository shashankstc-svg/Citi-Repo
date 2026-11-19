
package com.splwg.cm.domain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.splwg.base.api.batch.SubmissionParameters;
import com.splwg.base.api.datatypes.Bool;
import com.splwg.base.api.datatypes.Date;
import com.splwg.base.api.lookup.RunStatusLookup;
import com.splwg.base.api.lookup.ThreadStatusLookup;
import com.splwg.base.api.sql.PreparedStatement;
import com.splwg.base.api.sql.SQLResultRow;
import com.splwg.base.api.testers.BatchJobTestCase;
import com.splwg.base.domain.batch.batchControl.BatchControl_Id;
import com.splwg.base.domain.batch.batchRun.BatchRun;
import com.splwg.base.domain.batch.batchRun.BatchThread;
import com.splwg.base.domain.common.currency.Currency_Id;
import com.splwg.base.domain.security.user.User_Id;
import com.splwg.ccb.api.lookup.CustomerReadLookup;
import com.splwg.ccb.api.lookup.ServiceAgreementStatusLookup;
import com.splwg.ccb.domain.admin.cisDivision.CisDivision_Id;
import com.splwg.ccb.domain.admin.serviceAgreementType.ServiceAgreementType_Id;
import com.splwg.ccb.domain.customerinfo.account.Account_Id;
import com.splwg.ccb.domain.customerinfo.serviceAgreement.ServiceAgreement;
import com.splwg.ccb.domain.customerinfo.serviceAgreement.ServiceAgreement_DTO;
import com.splwg.ccb.domain.customerinfo.serviceAgreement.ServiceAgreement_Id;
import com.splwg.shared.common.ApplicationError;

public class cmrunBatchTestCase_Test extends BatchJobTestCase {

    private final String FILEPATH = "@SHARED_DIR";

    @Override
    protected SubmissionParameters setupRun(SubmissionParameters arg0) {

        return null;
    }

    @Override
    protected void validateResults(BatchRun batchRun) {

        assertEquals("BatchRun is expected to complete successfully", RunStatusLookup.constants.COMPLETE, batchRun
                .getRunStatus());
        for (Iterator iter = batchRun.getThreads().iterator(); iter.hasNext();) {
            BatchThread batchThread = (BatchThread) iter.next();
            assertEquals("BatchThread number " + batchThread.getId().getBatchThreadNumber()
                    + " is expected to complete successfully", ThreadStatusLookup.constants.COMPLETE, batchThread
                    .getThreadStatus());
        }

    }

    @Override
    public void testRerun() {

    }

    @Override
    public void testCompleteMultiThreadedRun() {

    }

    @Override
    public void testCompleteSingleThreadedRun() {

        //create person
        //createCustomer();
    	runPaytm();
    	//runpp();
    	//runBsegGen();
    	//runGl();
    	//runBillCancel();
    	
    	//runCMACCSL();
    	//runCMACCSL1();
    	//runBlpym();
    	//runTxnBc();
    	//runCitDirect();
    	///runUsg();
    	
    	//runUAccr();
    	
    	//runRebateCalc();
    	//runUpdNcv();
    	//runRbsb();//C:\spl\ouafsdk_4_5_0_1_1\ORMB7000\eclipseWorkspace
        
    }

    public void runCitDirect()
    {
    	createPreparedStatement("update CI_BILL_CHAR set srch_char_val='N',CHAR_VAL='N' where bill_id ='089994056648' and char_type_cd ='CMCDEXRT'").execute();
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("CM-CITDR"));
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
//        defaultParms.setIsTracingProgramStart(Bool.TRUE);
//        defaultParms.setIsTracingProgramEnd(Bool.TRUE);
//        defaultParms.setIsTracingSQL(Bool.TRUE);
//        defaultParms.setIsTracingStandardOut(Bool.TRUE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");
        defaultParms.setProcessDate(new Date(2025,3,31));
        

        Properties extraParameters = defaultParms.getExtraParameters();
        extraParameters.setProperty("queryManagedContent", "CM-PAYOUTDTLS");
        extraParameters.setProperty("extendableLookUpBo", "CM-DEBTDTLS");
        //extraParameters.setProperty("DIST-THD-POOL", "DEFAULT");
        //extraParameters.setProperty("restrictToBusinessObject", "CMPWBINBOUNDBO");
        submitBatchJob(defaultParms);
    }
    
    public void runCMACCSL()
    {
//    	createPreparedStatement("update CI_BILL_CHG set BILLABLE_CHG_STAT ='10'"
//    			+ " where sa_Id ='5024267762' and descr_on_bill='Accruals Transaction Feed Billable Charge'").execute();
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("CM-ACCSL"));
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
//        defaultParms.setIsTracingProgramStart(Bool.TRUE);
//        defaultParms.setIsTracingProgramEnd(Bool.TRUE);
//        defaultParms.setIsTracingSQL(Bool.TRUE);
//        defaultParms.setIsTracingStandardOut(Bool.TRUE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");
        //defaultParms.setProcessDate(new Date(2026,9,17));
        
        

        Properties extraParameters = defaultParms.getExtraParameters();
        extraParameters.setProperty("rateSchedules", "RATETIER,ICPER,ICMINUS,PERFBON");
        //extraParameters.setProperty("DIST-THD-POOL", "DEFAULT");
        //extraParameters.setProperty("restrictToBusinessObject", "CMPWBINBOUNDBO");
        submitBatchJob(defaultParms);
    }
    
    
    public void runUsg()
    {
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("CMADJUSG"));
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
//        defaultParms.setIsTracingProgramStart(Bool.TRUE);
//        defaultParms.setIsTracingProgramEnd(Bool.TRUE);
//        defaultParms.setIsTracingSQL(Bool.TRUE);
//        defaultParms.setIsTracingStandardOut(Bool.TRUE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");
        defaultParms.setProcessDate(new Date(2026,1,28));
        

        Properties extraParameters = defaultParms.getExtraParameters();
        extraParameters.setProperty("division", "GBL");
        //extraParameters.setProperty("DIST-THD-POOL", "DEFAULT");
        //extraParameters.setProperty("restrictToBusinessObject", "CMPWBINBOUNDBO");
        submitBatchJob(defaultParms);
    }
    
    public void runBillCancel()
    {
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("CM-BILCN"));
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
//        defaultParms.setIsTracingProgramStart(Bool.TRUE);
//        defaultParms.setIsTracingProgramEnd(Bool.TRUE);
//        defaultParms.setIsTracingSQL(Bool.TRUE);
//        defaultParms.setIsTracingStandardOut(Bool.TRUE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");
        defaultParms.setProcessDate(new Date(2026,1,28));
        

        Properties extraParameters = defaultParms.getExtraParameters();
        extraParameters.setProperty("billCycleCodes", "QTR2");
        extraParameters.setProperty("billCancelReason", "MASS");
        //extraParameters.setProperty("DIST-THD-POOL", "DEFAULT");
        //extraParameters.setProperty("restrictToBusinessObject", "CMPWBINBOUNDBO");
        submitBatchJob(defaultParms);
    }
    
    
    public void runUAccr()
    {
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("CM_ACRRP"));
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
//        defaultParms.setIsTracingProgramStart(Bool.TRUE);
//        defaultParms.setIsTracingProgramEnd(Bool.TRUE);
//        defaultParms.setIsTracingSQL(Bool.TRUE);
//        defaultParms.setIsTracingStandardOut(Bool.TRUE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");
        defaultParms.setProcessDate(new Date(2026,1,28));
        

        Properties extraParameters = defaultParms.getExtraParameters();
        extraParameters.setProperty("outputFileName", "accrualReport");
        extraParameters.setProperty("outputFilePath", "C://spl//ORMB7000//citidirect");
        extraParameters.setProperty("trialBillId", "315840500103");
        submitBatchJob(defaultParms);
    }
    
    public void runCMACCSL1()
    {
    	
    	createPreparedStatement("update CI_ACCT set EXCL_ACCR_SW='Y' where acct_id !='0841823148'").execute();
    	createPreparedStatement("delete from c1_accrual where accr_id='0841823000001065290'").execute();
    	createPreparedStatement("delete from c1_accrual_dtls where accr_id='0841823000001065290'").execute();
    	createPreparedStatement("delete from c1_accrual_calc_line where accr_id='0841823000001065290'").execute();
    	
    	
    	
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("C1_ACCAL"));
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
//        defaultParms.setIsTracingProgramStart(Bool.TRUE);
//        defaultParms.setIsTracingProgramEnd(Bool.TRUE);
//        defaultParms.setIsTracingSQL(Bool.TRUE);
//        defaultParms.setIsTracingStandardOut(Bool.TRUE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");
        defaultParms.setProcessDate(new Date(2026,5,20));
        

        Properties extraParameters = defaultParms.getExtraParameters();
        extraParameters.setProperty("division", "GBL");
        extraParameters.setProperty("accrualCycle", "ACGL");
        
        
        //extraParameters.setProperty("DIST-THD-POOL", "DEFAULT");
        //extraParameters.setProperty("restrictToBusinessObject", "CMPWBINBOUNDBO");
        submitBatchJob(defaultParms);
    }
    
    
    
    
    public void runBlpym()
    {
    	
//    	createPreparedStatement("delete  from cm_match_bill_pay a   where a.acct_nbr ='5472330011323564' and a.batch_cd='CMBLPYM '").execute();
//    	createPreparedStatement("update cm_match_bill_pay set MEVT_STATUS_FLG='H',CM_PAY_AMT=0, CM_BAL_AMT=0, CM_CLSDT=null,CM_PAYMENT_ID='', CM_TXN_CODE_TYPE='', CM_PAY_AMT_REMAIN=0, CM_PAY_SEG_ID='', CM_ALLOW_NEXT_STL='' where acct_nbr ='5472330011323564'").execute();
//    	createPreparedStatement("update cm_match_bill_pay set MEVT_STATUS_FLG='O' where acct_nbr ='5472330011323564' and MATCH_EVT_ID='97'").execute();
//    	createPreparedStatement("update cm_payment_detail_stg set cm_is_usd_pay ='N' where cm_payment_id in(select CM_PAYMENT_ID from cm_match_bill_pay    where acct_nbr ='5472330011323564')").execute();
    	
    	
    	
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("CMBLPYM"));
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
//        defaultParms.setIsTracingProgramStart(Bool.TRUE);
//        defaultParms.setIsTracingProgramEnd(Bool.TRUE);
//        defaultParms.setIsTracingSQL(Bool.TRUE);
//        defaultParms.setIsTracingStandardOut(Bool.TRUE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");
        defaultParms.setProcessDate(new Date(2026,6,12));
        

        Properties extraParameters = defaultParms.getExtraParameters();
        extraParameters.setProperty("accountNumberToProcess", "5569370000764687");
        //extraParameters.setProperty("division", "GBL");
        
        
        //extraParameters.setProperty("DIST-THD-POOL", "DEFAULT");
        //extraParameters.setProperty("restrictToBusinessObject", "CMPWBINBOUNDBO");
        submitBatchJob(defaultParms);
    }
    
    
    
    public void runTxnBc()
    {
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("CM-TXNBC"));
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
        defaultParms.setIsTracingProgramStart(Bool.FALSE);
        defaultParms.setIsTracingProgramEnd(Bool.FALSE);
        defaultParms.setIsTracingSQL(Bool.FALSE);
        defaultParms.setIsTracingStandardOut(Bool.FALSE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");
        Properties extraParameters = defaultParms.getExtraParameters();
        extraParameters.setProperty("division", "GBL");
        submitBatchJob(defaultParms);
    }
    
    
    /**
     * This method will create person
     * */
    public void runGl()
    {
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("CMGLEXJH"));//CMGLEXJH//CMGLEXJD
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
        defaultParms.setIsTracingProgramStart(Bool.FALSE);
        defaultParms.setIsTracingProgramEnd(Bool.FALSE);
        defaultParms.setIsTracingSQL(Bool.FALSE);
        defaultParms.setIsTracingStandardOut(Bool.FALSE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");

        Properties extraParameters = defaultParms.getExtraParameters();
//        extraParameters.setProperty("queryManagedContent", "CM_Fetch_GLExtract_Dtl");
//        extraParameters.setProperty("filePathParm", "C:/spl/ORMB7000/temp");
//        extraParameters.setProperty("batchName", "CMGLEXJD");
//        extraParameters.setProperty("extendableLookUpBo", "CM-GLACC");
//        extraParameters.setProperty("MAX-ERRORS", "500");
//        extraParameters.setProperty("DIST-THD-POOL", "am");
        
        extraParameters.setProperty("queryManagedContent", "CM_Fetch_GLExtract_Hdr");
        extraParameters.setProperty("filePathParm", "C:/spl/ORMB7000/temp");
        extraParameters.setProperty("batchName", "CMGLEXJD");
        extraParameters.setProperty("extendableLookUpBo", "CM-GLACC");
        extraParameters.setProperty("MAX-ERRORS", "500");
        extraParameters.setProperty("DIST-THD-POOL", "am");
        submitBatchJob(defaultParms);
    }

    public void createCustomer()
    {
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("CM-CDGRX"));
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
        defaultParms.setIsTracingProgramStart(Bool.TRUE);
        defaultParms.setIsTracingProgramEnd(Bool.TRUE);
        defaultParms.setIsTracingSQL(Bool.TRUE);
        defaultParms.setIsTracingStandardOut(Bool.TRUE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");

        Properties extraParameters = defaultParms.getExtraParameters();
        extraParameters.setProperty("filePathName", "C://spl//ORMB7000//citidirect");
        //extraParameters.setProperty("restrictToBusinessObject", "CMPWBINBOUNDBO");
        submitBatchJob(defaultParms);
    }
    
    
    public void runpp()
    {
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("CMPOSTPR"));
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
        defaultParms.setIsTracingProgramStart(Bool.TRUE);
        defaultParms.setIsTracingProgramEnd(Bool.TRUE);
        defaultParms.setIsTracingSQL(Bool.TRUE);
        defaultParms.setIsTracingStandardOut(Bool.TRUE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");

        Properties extraParameters = defaultParms.getExtraParameters();
        extraParameters.setProperty("billCycleCharType", "CMBLGCYC");
        extraParameters.setProperty("DIST-THD-POOL", "DEFAULT");
        //extraParameters.setProperty("restrictToBusinessObject", "CMPWBINBOUNDBO");
        submitBatchJob(defaultParms);
    }

    
    
    
    public void runPaytm()
    {
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("CM-PAYTM"));
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
//        defaultParms.setIsTracingProgramStart(Bool.TRUE);
//        defaultParms.setIsTracingProgramEnd(Bool.TRUE);
//        defaultParms.setIsTracingSQL(Bool.TRUE);
//        defaultParms.setIsTracingStandardOut(Bool.TRUE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");

        Properties extraParameters = defaultParms.getExtraParameters();
        extraParameters.setProperty("deliquencyStatusCharTypeCode", "CMDLQSTS");
        extraParameters.setProperty("deliquencyBucketCharTypeCode", "CMDLQNBT");
        extraParameters.setProperty("rebateFeesCharTypeCode", "CMRBFEES");
        extraParameters.setProperty("accountTypeCharTypeCode", "CMBLTYPE");
        extraParameters.setProperty("cBDaysToPayMethodCharTypeCode", "CMCBDTPY");
        extraParameters.setProperty("iBDaysToPayMethodCharTypeCode", "CMIBDTPY");
        extraParameters.setProperty("payTimeMethodCharTypeCode", "CMPYTMTH");
        extraParameters.setProperty("spendBucketPrioCharTypeCode", "CMSPBTP");
        submitBatchJob(defaultParms);
    }
    
    
    public void runUpdInv()
    {
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("CM-PAYTM"));
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
//        defaultParms.setIsTracingProgramStart(Bool.TRUE);
//        defaultParms.setIsTracingProgramEnd(Bool.TRUE);
//        defaultParms.setIsTracingSQL(Bool.TRUE);
//        defaultParms.setIsTracingStandardOut(Bool.TRUE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");

        Properties extraParameters = defaultParms.getExtraParameters();
        extraParameters.setProperty("deliquencyStatusCharTypeCode", "CMDLQSTS");
        extraParameters.setProperty("deliquencyBucketCharTypeCode", "CMDLQNBT");
        extraParameters.setProperty("rebateFeesCharTypeCode", "CMRBFEES");
        extraParameters.setProperty("accountTypeCharTypeCode", "CMBLTYPE");
        extraParameters.setProperty("cBDaysToPayMethodCharTypeCode", "CMCBDTPY");
        extraParameters.setProperty("iBDaysToPayMethodCharTypeCode", "CMIBDTPY");
        extraParameters.setProperty("payTimeMethodCharTypeCode", "CMPYTMTH");
        extraParameters.setProperty("spendBucketPrioCharTypeCode", "CMSPBTP");
        submitBatchJob(defaultParms);
    }
    
    public void runBsegGen()
    {
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("CMBSGEN"));
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
//        defaultParms.setIsTracingProgramStart(Bool.TRUE);
//        defaultParms.setIsTracingProgramEnd(Bool.TRUE);
//        defaultParms.setIsTracingSQL(Bool.TRUE);
//        defaultParms.setIsTracingStandardOut(Bool.TRUE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");

        Properties extraParameters = defaultParms.getExtraParameters();
        extraParameters.setProperty("billCycleCharTypeCode", "CMBLGCYC");
        extraParameters.setProperty("billCycleCd", "DEF");
        extraParameters.setProperty("freezeBillSegments", "N");

        submitBatchJob(defaultParms);
    }
    
    
    public void runRebateCalc()
    {
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("CM_RCDRB"));
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
//        defaultParms.setIsTracingProgramStart(Bool.TRUE);
//        defaultParms.setIsTracingProgramEnd(Bool.TRUE);
//        defaultParms.setIsTracingSQL(Bool.TRUE);
//        defaultParms.setIsTracingStandardOut(Bool.TRUE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");

        Properties extraParameters = defaultParms.getExtraParameters();
        extraParameters.setProperty("outputFilePath", "C:/spl/ORMB7000/temp");
 
     

        submitBatchJob(defaultParms);
    }
    public void runUpdNcv()
    {
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("CMUPDNCV"));
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
//        defaultParms.setIsTracingProgramStart(Bool.TRUE);
//        defaultParms.setIsTracingProgramEnd(Bool.TRUE);
//        defaultParms.setIsTracingSQL(Bool.TRUE);
//        defaultParms.setIsTracingStandardOut(Bool.TRUE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");

//        Properties extraParameters = defaultParms.getExtraParameters();
//        extraParameters.setProperty("outputFilePath", "C:/spl/ORMB7000/temp");
 
     

        submitBatchJob(defaultParms);
    }
    
    public void runRbsb()
    {
        SubmissionParameters defaultParms = createDefaultParameters(1);

        defaultParms.setBatchControlId(new BatchControl_Id("CM-RBSMR"));
        defaultParms.setBatchRerunNumber(BigInteger.ZERO);
        defaultParms.setBatchThreadNumber(new BigInteger("1"));
//        defaultParms.setIsTracingProgramStart(Bool.TRUE);
//        defaultParms.setIsTracingProgramEnd(Bool.TRUE);
//        defaultParms.setIsTracingSQL(Bool.TRUE);
//        defaultParms.setIsTracingStandardOut(Bool.TRUE);
        defaultParms.setLanguageId(getActiveContextLanguage().getId());
        defaultParms.setMaxExecutionAttempts(BigInteger.valueOf(1));
        defaultParms.setUserId(new User_Id("SYSUSER"));
        defaultParms.setUserPassword("sysuser00");

//        Properties extraParameters = defaultParms.getExtraParameters();
//        extraParameters.setProperty("outputFilePath", "C:/spl/ORMB7000/temp");
        Properties extraParameters = defaultParms.getExtraParameters();
        extraParameters.setProperty("outputFilePath", "C:/spl/ouafsdk_4_5_0_1_1/ORMB7000/eclipseWorkspace/cm");
        extraParameters.setProperty("outputFileName", "test");
        extraParameters.setProperty("status", "P");
     
 
     

        submitBatchJob(defaultParms);
    }


}
