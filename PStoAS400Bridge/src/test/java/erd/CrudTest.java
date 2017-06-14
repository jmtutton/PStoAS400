package erd;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

import erd.model.PsAccomplishment;
import erd.model.PsActionReason;
import erd.model.PsAddress;
import erd.model.PsCitizenship;
import erd.model.PsContractData;
import erd.model.PsCountry;
import erd.model.PsDiversityEthnicity;
import erd.model.PsDriversLicense;
import erd.model.PsEmergencyContact;
import erd.model.PsEmployeeChecklist;
import erd.model.PsEmployeeReview;
import erd.model.PsEmployment;
import erd.model.PsEthnicGroup;
import erd.model.PsHrsSource;
import erd.model.PsJob;
import erd.model.PsJobCode;
import erd.model.PsLocation;
import erd.model.PsName;
import erd.model.PsOrigHirEmpVw;
import erd.model.PsApplicantReferralSource;
import erd.model.PsPersonalDataEffectiveDated;
import erd.model.PsPersonalNationalId;
import erd.model.PsPerson;
import erd.model.PsPersonalData;
import erd.model.PsPersonalPhone;
import erd.model.CrossReferenceMultipleEmployeeId;
import erd.model.CrossReferenceCompany;
import erd.model.CrossReferenceEmployeeId;
import erd.model.CrossReferenceEthnicCode;
import erd.model.CrossReferenceJobCode;
import erd.model.CrossReferencePersonOfInterest;
import erd.model.CrossReferenceReferralSource;
import erd.model.CrossReferenceTerminationReason;
import erd.model.PsVariable;

public class CrudTest {
	
	int MAX_RESULTS = 10;
	
	@Test
	public void testPsAccomplishment() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsAccomplishment> query =
				em.createNamedQuery("PsAccomplishment.findAll", PsAccomplishment.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsAccomplishment> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsActnReasonTbl() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsActionReason> query =
				em.createNamedQuery("PsActionReason.findAll", PsActionReason.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsActionReason> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsAddress() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsAddress> query =
				em.createNamedQuery("PsAddress.findAll", PsAddress.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsAddress> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsCitizenship() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsCitizenship> query =
				em.createNamedQuery("PsCitizenship.findAll", PsCitizenship.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsCitizenship> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsContractData() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsContractData> query =
				em.createNamedQuery("PsContractData.findAll", PsContractData.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsContractData> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsCountryTbl() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsCountry> query =
				em.createNamedQuery("PsCountry.findAll", PsCountry.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsCountry> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsDiversEthnic() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsDiversityEthnicity> query =
				em.createNamedQuery("PsDiversityEthnicity.findAll", PsDiversityEthnicity.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsDiversityEthnicity> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsDriversLic() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsDriversLicense> query =
				em.createNamedQuery("PsDriversLicense.findAll", PsDriversLicense.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsDriversLicense> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsEmergencyCntct() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsEmergencyContact> query =
				em.createNamedQuery("PsEmergencyContact.findAll", PsEmergencyContact.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsEmergencyContact> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsEmplChecklist() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsEmployeeChecklist> query =
				em.createNamedQuery("PsEmployeeChecklist.findAll", PsEmployeeChecklist.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsEmployeeChecklist> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsEmployeeReview() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsEmployeeReview> query =
				em.createNamedQuery("PsEmployeeReview.findAll", PsEmployeeReview.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsEmployeeReview> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsEmployment() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsEmployment> query =
				em.createNamedQuery("PsEmployment.findAll", PsEmployment.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsEmployment> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsEthnicGrpTbl() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsEthnicGroup> query =
				em.createNamedQuery("PsEthnicGroup.findAll", PsEthnicGroup.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsEthnicGroup> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsHrsSourceI() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsHrsSource> query =
				em.createNamedQuery("PsHrsSource.findAll", PsHrsSource.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsHrsSource> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsJob() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsJob> query =
				em.createNamedQuery("PsJob.findAll", PsJob.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsJob> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsJobCodeTbl() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsJobCode> query =
				em.createNamedQuery("PsJobCode.findAll", PsJobCode.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsJobCode> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsLocationTbl() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsLocation> query =
				em.createNamedQuery("PsLocation.findAll", PsLocation.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsLocation> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsName() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsName> query =
				em.createNamedQuery("PsName.findAll", PsName.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsName> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsOrigHirEmpVw() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsOrigHirEmpVw> query =
				em.createNamedQuery("PsOrigHirEmpVw.findAll", PsOrigHirEmpVw.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsOrigHirEmpVw> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsPersApplRef() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsApplicantReferralSource> query =
				em.createNamedQuery("PsApplicantReferralSource.findAll", PsApplicantReferralSource.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsApplicantReferralSource> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsPersDataEffdt() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsPersonalDataEffectiveDated> query =
				em.createNamedQuery("PsPersonalDataEffectiveDated.findAll", PsPersonalDataEffectiveDated.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsPersonalDataEffectiveDated> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsPersNid() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsPersonalNationalId> query =
				em.createNamedQuery("PsPersonalNationalId.findAll", PsPersonalNationalId.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsPersonalNationalId> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsPerson() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsPerson> query =
				em.createNamedQuery("PsPerson.findAll", PsPerson.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsPerson> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsPersonalData() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsPersonalData> query =
				em.createNamedQuery("PsPersonalData.findAll", PsPersonalData.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsPersonalData> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsPersonalPhone() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsPersonalPhone> query =
				em.createNamedQuery("PsPersonalPhone.findAll", PsPersonalPhone.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsPersonalPhone> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsZhrrMultplEid() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<CrossReferenceMultipleEmployeeId> query =
				em.createNamedQuery("CrossReferenceMultipleEmployeeId.findAll", CrossReferenceMultipleEmployeeId.class);
		query.setMaxResults(MAX_RESULTS);
		List<CrossReferenceMultipleEmployeeId> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsZhrtCmpnyCref() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<CrossReferenceCompany> query =
				em.createNamedQuery("CrossReferenceCompany.findAll", CrossReferenceCompany.class);
		query.setMaxResults(MAX_RESULTS);
		List<CrossReferenceCompany> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsZhrtEmpIdCref() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<CrossReferenceEmployeeId> query =
				em.createNamedQuery("CrossReferenceEmployeeId.findAll", CrossReferenceEmployeeId.class);
		query.setMaxResults(MAX_RESULTS);
		List<CrossReferenceEmployeeId> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsZhrtEthCdCref() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<CrossReferenceEthnicCode> query =
				em.createNamedQuery("CrossReferenceEthnicCode.findAll", CrossReferenceEthnicCode.class);
		query.setMaxResults(MAX_RESULTS);
		List<CrossReferenceEthnicCode> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsZhrtJobCdCref() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<CrossReferenceJobCode> query =
				em.createNamedQuery("CrossReferenceJobCode.findAll", CrossReferenceJobCode.class);
		query.setMaxResults(MAX_RESULTS);
		List<CrossReferenceJobCode> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsZhrtPerPoiTr() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<CrossReferencePersonOfInterest> query =
				em.createNamedQuery("CrossReferencePersonOfInterest.findAll", CrossReferencePersonOfInterest.class);
		query.setMaxResults(MAX_RESULTS);
		List<CrossReferencePersonOfInterest> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsZhrtRfSrcCref() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<CrossReferenceReferralSource> query =
				em.createNamedQuery("CrossReferenceReferralSource.findAll", CrossReferenceReferralSource.class);
		query.setMaxResults(MAX_RESULTS);
		List<CrossReferenceReferralSource> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsZhrtTrmrsCref() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<CrossReferenceTerminationReason> query =
				em.createNamedQuery("CrossReferenceTerminationReason.findAll", CrossReferenceTerminationReason.class);
		query.setMaxResults(MAX_RESULTS);
		List<CrossReferenceTerminationReason> results = query.getResultList();
		assertNotNull(results);
	}
	
	@Test
	public void testPsZpttVariable() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PStoAS400Bridge");
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<PsVariable> query =
				em.createNamedQuery("PsZpttVariable.findAll", PsVariable.class);
		query.setMaxResults(MAX_RESULTS);
		List<PsVariable> results = query.getResultList();
		assertNotNull(results);
	}

}
