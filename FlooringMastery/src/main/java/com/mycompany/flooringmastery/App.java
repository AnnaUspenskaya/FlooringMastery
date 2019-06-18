package com.mycompany.flooringmastery;

import com.mycompany.flooringmastery.controller.FlooringMasteryController;
import com.mycompany.flooringmastery.dao.FlooringMasteryDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.mycompany.flooringmastery.dao.ProductDAO;
import com.mycompany.flooringmastery.dao.ProductDAOFileImpl;
import com.mycompany.flooringmastery.dao.TaxDAO;
import com.mycompany.flooringmastery.dao.TaxDAOFileImpl;
import com.mycompany.flooringmastery.dao.TrainingModeDaoFileImpl;
import com.mycompany.flooringmastery.service.FlooringMasteryServiceLayer;
import com.mycompany.flooringmastery.service.FlooringMasteryServiceLayerFileImpl;
import com.mycompany.flooringmastery.ui.FlooringMasteryView;
import com.mycompany.flooringmastery.ui.UserIO;
import com.mycompany.flooringmastery.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Anna
 */
public class App {

    public static void main(String[] args) {
//        UserIO myIo = new UserIOConsoleImpl();
//        FlooringMasteryView myView = new FlooringMasteryView(myIo);
//        TaxDAO myTax = new TaxDAOFileImpl();
//        ProductDAO myProduct = new ProductDAOFileImpl();
//        FlooringMasteryDao dao = new FlooringMasteryDaoFileImpl();
//        FlooringMasteryDao trainingDao = new TrainingModeDaoFileImpl();
//        FlooringMasteryServiceLayer service = new FlooringMasteryServiceLayerFileImpl(myTax, myProduct, dao);
//        FlooringMasteryController controller = new FlooringMasteryController(service, myView);
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = ctx.getBean("controller", FlooringMasteryController.class);
        controller.run();
    }
}
