package com.nix.jdbcdaotask;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class JdbcRoleDaoTest {

    private static IDatabaseTester tester = null;
    private static String DB_URI = null; /* = "jdbc:h2:mem:default;DB_CLOSE_DELAY=-1;init=runscript from './lib/tempbdinit.sql'";*/
    private static String DB_DRIVER = null; /* = "org.h2.Driver";*/

    private static final Logger logger = LoggerFactory.getLogger(JdbcRoleDaoTest.class);

    @BeforeClass
    public static void setUpClass() throws Exception {
        Properties properties = loadProperties();
        DB_DRIVER = properties.getProperty("db.driver");
        DB_URI = properties.getProperty("db.uri");
        tester = new JdbcDatabaseTester(DB_DRIVER, DB_URI);
        tester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
    }

    @Before
    public void setUp() throws Exception {
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(JdbcRoleDaoTest.class.
                getClassLoader().getResource("testDbData.xml"));
        tester.setDataSet(dataSet);
        tester.onSetup();
    }

    @After
    public void tearDown() throws Exception {
        tester.onTearDown();
    }

    @Test
    public void testCreate() throws Exception {
        JdbcRoleDao roleDao = new JdbcRoleDao(DB_DRIVER, DB_URI);

        roleDao.create(new Role(3L, "Director"));

        IDataSet actualDataSet = tester.getConnection().createDataSet();
        ITable actualTable = actualDataSet.getTable("USER_ROLE");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(JdbcRoleDaoTest.class.
                getClassLoader().getResource("testCreateRoleExpected.xml"));
        ITable expectedTable = expectedDataSet.getTable("USER_ROLE");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testUpdate() throws Exception {
        JdbcRoleDao roleDao = new JdbcRoleDao(DB_DRIVER, DB_URI);

        roleDao.update(new Role(1L, "Director"));

        IDataSet actualDataSet = tester.getConnection().createDataSet();
        ITable actualTable = actualDataSet.getTable("USER_ROLE");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(JdbcRoleDaoTest.class.
                getClassLoader().getResource("testUpdateRoleExpected.xml"));
        ITable expectedTable = expectedDataSet.getTable("USER_ROLE");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testRemove() throws Exception {
        JdbcRoleDao roleDao = new JdbcRoleDao(DB_DRIVER, DB_URI);

        roleDao.remove(new Role(1L, "Admin"));

        IDataSet actualDataSet = tester.getConnection().createDataSet();
        ITable actualTable = actualDataSet.getTable("USER_ROLE");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(JdbcRoleDaoTest.class.
                getClassLoader().getResource("testRemoveRoleExpected.xml"));
        ITable expectedTable = expectedDataSet.getTable("USER_ROLE");

        Assertion.assertEquals(expectedTable, actualTable);

    }

    @Test
    public void testFindByName() throws Exception {
        JdbcRoleDao roleDao = new JdbcRoleDao(DB_DRIVER, DB_URI);

        Role role = roleDao.findByName("Admin");

        Assert.assertEquals("No records found by Role name", "Admin", role.getName());
        Assert.assertEquals("No records found by Role name", new Long(1), role.getId());

    }

    @Test(expected = NullPointerException.class)
    public void testCreateNull() {
        JdbcRoleDao roleDao = new JdbcRoleDao(DB_DRIVER, DB_URI);

        roleDao.create(null);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateNull() {
        JdbcRoleDao roleDao = new JdbcRoleDao(DB_DRIVER, DB_URI);

        roleDao.update(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNull() {
        JdbcRoleDao roleDao = new JdbcRoleDao(DB_DRIVER, DB_URI);

        roleDao.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void testFindByNameNull() {
        JdbcRoleDao roleDao = new JdbcRoleDao(DB_DRIVER, DB_URI);

        roleDao.findByName(null);
    }

    private static Properties loadProperties() {
        Properties properties = null;
        InputStream in = null;
        try {
            properties = new Properties();
            in = AbstractJdbcDao.class.getResourceAsStream("/.h2.testserver.properties");
            properties.load(in);
        } catch (Exception e) {
            logger.error("properties loading failure", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                logger.error("cannot close the input stream", e);
            }
        }
        return properties;
    }


}



