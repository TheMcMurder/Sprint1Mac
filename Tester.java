/////////////////////////////////////////////////////////////////
///   This file is an example of an Object Relational Mapping in
///   the ISys Core at Brigham Young University.  Students
///   may use the code as part of the 413 course in their
///   milestones following this one, but no permission is given
///   to use this code is any other way.  Since we will likely
///   use this code again in a future year, please DO NOT post
///   the code to a web site, share it with others, or pass
///   it on in any way.

package edu.byu.isys414.jmcmurdi.IntexII;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

/**
 * Tests for the program.
 * 
 * See http://www.junit.org/apidocs/org/junit/Assert.html for the available assertions you can make.
 * 
 * @version 1.2
 */
public class Tester {

	// for comparing dates
	SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

	public Tester() throws Exception {
		CreateDB.main(null);
	}

	@Test
	public void TestRental() throws Exception {

		Rental c = BusinessObjectDAO.getInstance().create("Rental", "rentaltempitem1");

		c.setDateOut(SDF.parse("2012-3-1"));
		c.setDateDue(SDF.parse("2012-3-13"));
		c.setWorkOrderNum(32425);
		c.setForRentid("tempdata");
//		c.setPaymentid("muhahahaha");
//		c.setJournalentryid("Phycology 1510");
//		c.setCustomerid("NO ID FOR YOU");
//		c.setCommid("We don't share");
//		// c.setRevid("Resistance is futile");
//		c.setEmpid("The employee died");
//		c.setStoreid("The store burned down");
//		c.setTransdate(SDF.parse("1990-12-15"));
//		c.setSubtotal(1500);
//		c.setTax(23);
//		c.setTotal(1523);

		c.save();

		// Testing Cache

		Rental c2 = BusinessObjectDAO.getInstance().read("rentaltempitem1");
		assertTrue("Same", compareTwoObjects(c.getClass(), c, c2));
		c2.save();

		// Testing DB read and clearing cache
		Cache.getInstance().clear();
		Rental c3 = BusinessObjectDAO.getInstance().read("rentaltempitem1");

		assertTrue("Same", compareTwoObjects(c.getClass(), c3, c2));
		c3.save();

		// testing update of item;
		Cache.getInstance().clear();

		c3.setDateDue(SDF.parse("1990-12-14"));
		c3.setForRentid("tempdata2");
		c3.save();

		// assertTrue("after clearing cache 3rd time", compareTwoObjects(c.getClass(), c3, c2));

	}

	// @Test
	public void TestTransaction() throws Exception {
		Transaction c = BusinessObjectDAO.getInstance().create("Transaction", "Transactiontempitem1");

		c.setPaymentid("muhahahaha");
		c.setJournalentryid("Phycology 1510");
		c.setCustomerid("NO ID FOR YOU");
		c.setCommid("We don't share");
		// c.setRevid("Resistance is futile");
		c.setEmpid("The employee died");
		c.setStoreid("The store burned down");
		c.setTransdate(SDF.parse("1990-12-15"));
		c.setSubtotal(1500);
		c.setTax(23);
		c.setTotal(1523);

		c.save();

		// Testing Cache

		Transaction c2 = BusinessObjectDAO.getInstance().read("Transactiontempitem1");
		assertTrue("Same", compareTwoObjects(c.getClass(), c, c2));
		c2.save();

		// Testing DB read and clearing cache
		Cache.getInstance().clear();
		Transaction c3 = BusinessObjectDAO.getInstance().read("Transactiontempitem1");

		assertTrue("Same", compareTwoObjects(c.getClass(), c3, c2));
		c3.save();

		// testing update of item;
		Cache.getInstance().clear();

		c3.setTransdate(SDF.parse("1990-12-14"));
		c3.save();

		// assertTrue("after clearing cache 3rd time", compareTwoObjects(c.getClass(), c3, c2));

	}

	// @Test
	public void TestStoreProd() throws Exception {
		StoreProd c = BusinessObjectDAO.getInstance().create("StoreProd", "storeprodtempitem1");

		c.setQuantityleft(5);
		c.setCprodid("who give a rats tail");
		c.setStoreid("again I don't care");
		c.setShelflocation("it isn't really there");

		c.save();

		// Testing Cache

		StoreProd c2 = BusinessObjectDAO.getInstance().read("storeprodtempitem1");
		assertTrue("Same", compareTwoObjects(c.getClass(), c, c2));
		c2.save();

		// Testing DB read and clearing cache
		Cache.getInstance().clear();
		StoreProd c3 = BusinessObjectDAO.getInstance().read("storeprodtempitem1");

		assertTrue("Same", compareTwoObjects(c.getClass(), c3, c2));
		c3.save();

		// testing update of item;
		Cache.getInstance().clear();

		c3.setQuantityleft(6);
		c3.save();

		// assertTrue("after clearing cache 3rd time", compareTwoObjects(c.getClass(), c3, c2));

	}

	// @Test
	public void TestStore() throws Exception {
		Store c = BusinessObjectDAO.getInstance().create("Store", "storetempitem1");

		c.setManagerid("tempEmployee1");
		c.setLocation("Behind the shed");
		c.setPhone("Cup on the table with the string");
		c.setAddress("little dirt mount in backyard");
		c.setSalestaxrate(.067);
		c.setSubnetid("255.255.555");
		c.save();

		// BusinessObjectDAO.getInstance().save(c);

		// Testing Cache

		Store c2 = BusinessObjectDAO.getInstance().read("storetempitem1");
		assertTrue("Same", compareTwoObjects(c.getClass(), c, c2));
		c2.save();

		// Testing DB read and clearing cache
		Cache.getInstance().clear();
		Store c3 = BusinessObjectDAO.getInstance().read("storetempitem1");

		assertTrue("Same", compareTwoObjects(c.getClass(), c3, c2));
		c3.save();

		// testing update of item;
		Cache.getInstance().clear();

		c3.setSubnetid("255.255.253");
		c3.save();

		// assertTrue("after clearing cache 3rd time", compareTwoObjects(c.getClass(), c3, c2));

	}

	// @Test
	public void TestRevSource() throws Exception {
		RevSource c = BusinessObjectDAO.getInstance().create("RevSource", "RevSourcetempitem1");

		c.setChargeamount(15);
		c.setRevtype("something fake");
		c.setTransid("ppp");
		c.save();

		BusinessObjectDAO.getInstance().save(c);

		// Testing Cache

		RevSource c2 = BusinessObjectDAO.getInstance().read("RevSourcetempitem1");
		assertTrue("Same", compareTwoObjects(c.getClass(), c, c2));
		c2.save();

		// Testing DB read and clearing cache
		Cache.getInstance().clear();
		RevSource c3 = BusinessObjectDAO.getInstance().read("RevSourcetempitem1");

		assertTrue("Same", compareTwoObjects(c.getClass(), c3, c2));
		c3.save();

		// testing update of item;
		Cache.getInstance().clear();

		c3.setRevtype("Something fake");
		// c3.setPaychange(1);
		c3.save();

		// assertTrue("after clearing cache 3rd time", compareTwoObjects(c.getClass(), c3, c2));

	}

	// @Test
	public void TestPayment() throws Exception {
		Payment c = BusinessObjectDAO.getInstance().create("Payment", "paymenttempvalue1");

		c.setPayamount(15);
		c.setPaychange(15);
		c.setType("something fake");
		c.save();

		BusinessObjectDAO.getInstance().save(c);

		// Testing Cache

		Payment c2 = BusinessObjectDAO.getInstance().read("paymenttempvalue1");
		assertTrue("Same", compareTwoObjects(c.getClass(), c, c2));
		c2.save();

		// Testing DB read and clearing cache
		Cache.getInstance().clear();
		Payment c3 = BusinessObjectDAO.getInstance().read("paymenttempvalue1");

		assertTrue("Same", compareTwoObjects(c.getClass(), c3, c2));
		c3.save();

		// testing update of item;
		Cache.getInstance().clear();

		c3.setType("something else");
		// c3.setPaychange(1);
		c3.save();

		// assertTrue("after clearing cache 3rd time", compareTwoObjects(c.getClass(), c3, c2));

	}

	// @Test
	public void TestGeneralLedger() throws Exception {
		GeneralLedger c = BusinessObjectDAO.getInstance().create("GeneralLedger", "glvalue3");
		c.setAccountName("The fake name");
		c.setBalance(50);
		c.setType("the fake type");

		BusinessObjectDAO.getInstance().save(c);

		// Testing Cache

		GeneralLedger c2 = BusinessObjectDAO.getInstance().read("glvalue3");
		assertTrue("Same", compareTwoObjects(c.getClass(), c, c2));
		c2.save();

		// Testing DB read and clearing cache
		Cache.getInstance().clear();
		GeneralLedger c3 = BusinessObjectDAO.getInstance().read("glvalue3");

		assertTrue("Same", compareTwoObjects(c.getClass(), c3, c2));
		c3.save();

		/*
		 * //start tangent idea c3.setType("something else"); assertTrue("after clearing cache 3rd time", compareTwoObjects(c.getClass(), c3, c));
		 */

		// put this back if tangent idea borks it
		// testing update of item;
		Cache.getInstance().clear();

		c3.setType("something new");
		c3.save();

		// assertTrue("after clearing cache 3rd time", compareTwoObjects(c.getClass(), c3, c2));

	}

	// @Test
	public void TestCommission() throws Exception {
		System.out.println("test3");
		Commission c = BusinessObjectDAO.getInstance().create("Commission", "comm1");
		c.setEmpid("employee1");
		c.setComdate(SDF.parse("2011-12-12"));
		BusinessObjectDAO.getInstance().save(c);

		// Testing Cache

		Commission c2 = BusinessObjectDAO.getInstance().read("comm1");
		assertTrue("Same", compareTwoObjects(c.getClass(), c, c2));
		c2.save();

		// Testing DB read and clearing cache
		Cache.getInstance().clear();
		Commission c3 = BusinessObjectDAO.getInstance().read("comm1");

		assertTrue("Same", compareTwoObjects(c.getClass(), c3, c2));
		c3.save();

		// testing update of item;
		Cache.getInstance().clear();

		c3.setEmpid("employee2");
		c3.save();

		// assertTrue("Same", compareTwoObjects(c.getClass(), c3, c2));
	}

	// @Test
	public void TestJournalEntry() throws Exception {
		JournalEntry c = BusinessObjectDAO.getInstance().create("JournalEntry", "journalentrytestingtemp");
		c.setTransdate(SDF.parse("2012-05-05"));
		c.save();

		// testing cache
		JournalEntry c2 = BusinessObjectDAO.getInstance().read("journalentrytestingtemp");

		assertTrue("same", compareTwoObjects(c.getClass(), c, c2));

		// clearing cache

		Cache.getInstance().clear();

		JournalEntry c3 = BusinessObjectDAO.getInstance().read("journalentrytestingtemp");

		assertTrue("same", compareTwoObjects(c.getClass(), c3, c2));

		c3.save();

		// update item
		Cache.getInstance().clear();

		c.setTransdate(SDF.parse("2012-05-06"));
		c3.save();

		// assertTrue("same", compareTwoObjects(c.getClass(), c3, c2));

	}

	// @Test
	public void TestDebitCredit() throws Exception {

		DebitCredit c = BusinessObjectDAO.getInstance().create("DebitCredit", "debit230482");
		c.setJournalEntryid("journalentry17");
		c.setIsDebit(true);
		c.setGlName("Fake General Ldeget");
		c.setAmount(15);
		c.save();

		// testing cache
		DebitCredit c2 = BusinessObjectDAO.getInstance().read("debit230482");

		assertTrue("same", compareTwoObjects(c.getClass(), c, c2));

		// clearing cache

		Cache.getInstance().clear();

		DebitCredit c3 = BusinessObjectDAO.getInstance().read("debit230482");

		assertTrue("same", compareTwoObjects(c.getClass(), c3, c2));

		c3.save();

		// update item
		Cache.getInstance().clear();

		c3.setGlName("Anytihgfljsdla;fjsa");
		c3.save();

		// assertTrue("same", compareTwoObjects(c.getClass(), c3, c2));

	}

	// @Test
	public void TestCustomer() throws Exception {
		System.out.println("test1");
		Customer c = BusinessObjectDAO.getInstance().create("Customer", "custtk421");
		c.setFirstName("Frodo");
		c.setLastName("Baggins");
		c.setEmail("Frodo@gmail.com");
		c.setPhone("Hobbits don't have phones you fool of a took");
		c.setAddress("House under the hill - Bag end");
		c.setState("The Shire");
		c.setZip("2343223");
		c.save();

		// testing cache
		Customer c2 = BusinessObjectDAO.getInstance().read("custtk421");

		assertTrue("same", compareTwoObjects(c.getClass(), c, c2));

		// clearing cache

		Cache.getInstance().clear();

		Customer c3 = BusinessObjectDAO.getInstance().read("custtk421");

		assertTrue("same", compareTwoObjects(c.getClass(), c3, c2));

		c3.save();

		// update item
		Cache.getInstance().clear();

		c3.setAddress("soemthing else");
		c3.save();

		// assertTrue("same", compareTwoObjects(c.getClass(), c3, c2));

	}

	// @Test
	public void TestProductandpprod() throws Exception {

		PProduct c = BusinessObjectDAO.getInstance().create("PProduct", "pproducttemp1");
		c.setCost(15);
		c.setCprodid("anthing here");
		c.setDatepuchased(SDF.parse("2012-12-12"));
		c.setPprodcomrate(.025);
		c.setProdPrice(1500);
		c.setProdType("A false type");
		c.setSerialnum("DLFKJSDL:F");
		c.setShelflocation("Adamantium case on the floor");
		c.setStatus("SOLD");
		c.setStoreid("fake store1");
		c.save();

		// Testing Cache
		PProduct c2 = BusinessObjectDAO.getInstance().read("pproducttemp1");

		// Clearing Cache and testing DB read
		Cache.getInstance().clear();
		PProduct c3 = BusinessObjectDAO.getInstance().read("pproducttemp1");
		c3.save();

		assertTrue("Same", compareTwoObjects(c.getClass(), c, c2));
		assertTrue("Same", compareTwoObjects(c.getClass(), c3, c2));

		// should fail after this
		Cache.getInstance().clear();
		c3.setProdPrice(16);

		c3.save();
		// assertTrue("Same", compareTwoObjects(c.getClass(), c3, c2));

	}

	// @Test
	public void TestProductandCprod() throws Exception {
		// Product p = BusinessObjectDAO.getInstance().create("Product", "product1");
		// p.setprodPrice(15);
		// p.setprodType("Conceptual Product");
		CProduct c = BusinessObjectDAO.getInstance().create("CProduct", "cproduct1");
		c.setAvgCost(15.00);
		c.setDescription("like a boss");
		c.setManufacturer("Acme Corp");
		c.setProdName("Who gives a crap!");
		c.setCprodComRate(.025);
		c.save();

		// Testing Cache
		CProduct c2 = BusinessObjectDAO.getInstance().read("cproduct1");

		// Clearing Cache and testing DB read
		Cache.getInstance().clear();
		CProduct c3 = BusinessObjectDAO.getInstance().read("cproduct1");
		c3.save();

		assertTrue("Same", compareTwoObjects(c.getClass(), c, c2));
		assertTrue("Same", compareTwoObjects(c.getClass(), c3, c2));

		// should fail after this
		Cache.getInstance().clear();
		c3.setAvgCost(5);
		c3.save();
		// assertTrue("changed average cost", compareTwoObjects(c.getClass(), c3, c2));

	}

	/** Test the Employee BO */
	// @Test
	public void TestEmployee() throws Exception {
		System.out.println("test2");
		// System.out.println("1\n");
		Employee s = BusinessObjectDAO.getInstance().create("Employee", "emp1");
		s.setUsername("Maggie");
		// s.setPassword("Suck Suck (on binkie)");
		s.setBirthDate(new Date());
		// s.setIQ(200);
		s.setDistance(1000);
		// s.setSalary(100000.50);
		s.setFavoriteNumber(42);
		s.save();

		DebitCredit dc1 = BusinessObjectDAO.getInstance().create("DebitCredit", "dc100");
		dc1.save();

		// System.out.println("2\n Birthdate: " + s.getBirthDate());

		// since emp1 is in the Cache, this tests reading from the cache
		Employee s2 = BusinessObjectDAO.getInstance().read("emp1");
		assertTrue("Not Same s,s2", compareTwoObjects(s.getClass(), s, s2));

		// now clear the cache (you'd never do this in the real world)
		// then we can test reading from the database
		Cache.getInstance().clear();
		Employee s3 = BusinessObjectDAO.getInstance().read("emp1");

		// s3.setSalary(15);
		// s3.setIQ(3);
		// s3.setHiredate(SDF.parse("1985-02-21"));
		s3.save();

		// test deleting
		// BusinessObjectDAO.getInstance().delete(s);
		Employee s7 = BusinessObjectDAO.getInstance().create("Employee", "sljfdasl");
		s7.save();

		// System.out.println("Testing s and s2: ");

		// s2.setHiredate(null);
		// System.out.println("\n\n\n\n\n\n");
		// System.out.println("s2 hiredate value: " + s2.getHiredate());
		// System.out.println("s3 hiredate value: " + s3.getHiredate());
		// System.out.println("Testing s2 and s3: ");

		assertTrue("Not Same s2,s3", compareTwoObjects(s2.getClass(), s2, s3));

		s.setFirstname("Amy");
		s.save();
		Cache.getInstance().clear();
		Employee s9 = BusinessObjectDAO.getInstance().read("emp1");

		assertTrue("S, S9", compareTwoObjects(s.getClass(), s, s9));

	}

	/**
	 * This method takes two objects and using both recursion and reflection tests to see if they are the same.
	 * 
	 * @param boClass
	 * @param Obj1
	 * @param Obj2
	 * @return
	 */

	private boolean compareTwoObjects(Class boClass, Object Obj1, Object Obj2) {
		try {

			Map<String, Class> firstMap = getBusinessObjectFields(boClass);
			Class tempBOClass = boClass.getSuperclass();
			if (tempBOClass != BusinessObject.class) {
				if (compareTwoObjects(tempBOClass, Obj1, Obj2) == false) {
					return false;
				}
				tempBOClass = tempBOClass.getSuperclass();
			}

			for (Map.Entry<String, Class> entry : firstMap.entrySet()) {
				String fieldName = entry.getKey();
				Class fieldType = entry.getValue();

				Method method = getGetterMethod(boClass, fieldName, fieldType);

				method.invoke(Obj1);
				System.out.println(fieldName);

				if (fieldType == String.class) {
					// System.out.println("string");

					String string1 = (String) method.invoke(Obj1);
					String string2 = (String) method.invoke(Obj2);
					if (string1 != null && string2 != null) {
						if (method.invoke(Obj1).equals(method.invoke(Obj2))) {
							// System.out.println("values are the same");
						} else {
							// System.out.println("Values are not the same");
							return false;
						}
					} else {
						// System.out.println("one is null");
						if (string1 == null) {
							if (string2 == null) {

							} else {
								// System.out.println(fieldName+ " One value is null and the other is not");
								return false;
							}
						}
						if (string2 == null) {
							if (string1 == null) {

							} else {
								// System.out.println(fieldName+ " One value is null and the other is not");
								return false;
							}
						}
					}

				} // string
				else if (fieldType == Integer.class || fieldType == int.class) {
					// System.out.println("integer");

					int value1 = (Integer) method.invoke(Obj1);

					int value2 = (Integer) method.invoke(Obj2);

					if (value1 != 0 && value2 != 0) {
						if (method.invoke(Obj1).equals(method.invoke(Obj2))) {
							// System.out.println("values are the same");
						} else {
							// System.out.println("Values are not the same, value1 = "+ value1 + " value 2= " + value2);
							return false;
						}
					} else {
						// System.out.println("one is 0");
						if (value1 == 0) {
							if (value2 == 0) {

							} else {
								// System.out.println(fieldName+ " One value is 0 and the other is not");
								return false;
							}
						}
						if (value2 == 0) {
							if (value1 == 0) {

							} else {
								// System.out.println(fieldName+ " One value is 0 and the other is not");
								return false;
							}
						}
					}
				} // integer
				else if (fieldType == Double.class || fieldType == double.class) {

					// System.out.println("double");
					double value1 = (Double) method.invoke(Obj1);
					double value2 = (Double) method.invoke(Obj2);

					if (value1 != 0 && value2 != 0) {
						if (method.invoke(Obj1).equals(method.invoke(Obj2))) {
							// System.out.println("values are the same");
						} else {
							// System.out.println("Values are not the same, value1 = "+ value1 + " value 2= " + value2);
							return false;
						}
					} else {
						// System.out.println("one is 0");
						if (value1 == 0) {
							if (value2 == 0) {

							} else {
								// System.out.println(fieldName+ " One value is 0 and the other is not");
								return false;
							}
						}
						if (value2 == 0) {
							if (value1 == 0) {

							} else {
								// System.out.println(fieldName+ " One value is 0 and the other is not");
								return false;
							}
						}
					}

				} // double
				else if (fieldType == java.util.Date.class) {
					java.util.Date date1 = (java.util.Date) method.invoke(Obj1);
					java.util.Date date2 = (java.util.Date) method.invoke(Obj2);
					if (date1 != null && date2 != null) {
						if (date1.equals(date2)) {
							// System.out.println("same");
						} else {
							// System.out.println("not same " + fieldName + " "+ date1 + " and " + date2);
							return false;
						}
					} else {
						// System.out.println("one is null");
						if (date1 == null) {
							if (date2 == null) {
								// System.out.println("both null");
							} else {
								// System.out.println(fieldName+ "Not the same date(null1)");
								return false;
							}
						}
						if (date2 == null) {
							if (date1 == null) {
								// System.out.println("both null");
							} else {
								// System.out.println(fieldName+ "Not the same date(null2)");
								return false;
							}
						}
					}
				} // date
				else if (fieldType == Boolean.class || fieldType == boolean.class) {

					// System.out.println("boolean");

					boolean value1 = (Boolean) method.invoke(Obj1);
					boolean value2 = (Boolean) method.invoke(Obj2);

					if (value1 != value2) {
						// System.out.println("Booleans are not the same");
						return false;

					} else {
						// System.out.println("Booleans are the same");
					}

				} // boolean
				else {
					throw new DataException("Cannot read " + boClass.getName() + " from the database.  The BusinessObjectDAO does not support this data type: " + fieldType.getName());
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Gets all of the fields for a class.
	 * 
	 * @param boClass
	 * @return
	 * @throws Exception
	 */
	private Map<String, Class> getBusinessObjectFields(Class boClass) throws Exception {
		Map<String, Class> fields = new TreeMap<String, Class>();
		for (Field field : boClass.getDeclaredFields()) {
			if (field.getAnnotation(BusinessObjectField.class) != null) {
				fields.put(field.getName(), field.getType());
			}// if
		}// for
		return fields;
	}

	/** Returns the getter for the given field name */
	private Method getGetterMethod(Class boClass, String fieldName, Class fieldType) throws DataException {
		String getterName = "get" + fieldName.substring(0, 1).toUpperCase();
		if (fieldType == Boolean.class || fieldType == boolean.class) {
			getterName = "is" + fieldName.substring(0, 1).toUpperCase();
		}
		if (fieldName.length() > 1) {
			getterName += fieldName.substring(1);
		}// if
		try {
			Method method = boClass.getDeclaredMethod(getterName);
			if (method.getReturnType() == fieldType) {
				return method;
			}
		} catch (NoSuchMethodException nsme) {
			// pass so we throw the exception at the end
		}// try
		throw new DataException("Error in " + boClass.getName() + ".  No method named " + getterName + "(" + fieldType.getName() + ") to correspond with field " + fieldName + " in the database.");
	}

}