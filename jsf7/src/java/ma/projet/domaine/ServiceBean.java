/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import ma.projet.beans.Employe;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;
import ma.projet.beans.Service;
import ma.projet.service.EmployeService;
import ma.projet.service.EmployeService;
import ma.projet.service.ServiceService;
import ma.projet.service.ServiceService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Safae
 */

@ManagedBean(name = "serviceBean")
public class ServiceBean {
    
 private Employe employe;
    private Service service;
    private ServiceService serviceService;
    private List<Service> services;
    private List<Employe> employes;
    private EmployeService employeService;
    private static ChartModel barModel;

    public ServiceBean() {
        service = new Service();
        serviceService = new ServiceService();
        employe = new Employe();
        employeService = new EmployeService();
    }


    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public List<Service> getServices() {
        if (services == null) {
            services = serviceService.getAll();
        }
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String onCreateAction() {
        serviceService.create(service);
        service  = new Service();
        return null;
    }



    public void onCancel(RowEditEvent event) {
    }

    public void onEditm(RowEditEvent event) {
        employe = (Employe) event.getObject();
        Service service = serviceService.getById(this.employe.getService().getId());
        employe.setService(service);
        employe.getService().setNom(service.getNom());
        employeService.update(employe);
    }

        public void onDeleteAction() {
        serviceService.delete(service);

    }

    public void onEdit(RowEditEvent event) {
        service = (Service) event.getObject();
        serviceService.update(service);
    }
    
    public String onDeleteActionm() {

        employeService.delete(employeService.getById(employe.getId()));
        return null;
    }

    public List<Employe> serviceLoad() {
        for (Employe m : employeService.getAll()) {
            if (m.getService().equals(service)) {
                employes.add(m);
            }
        }
        return employes;

    }

    public void onCancelm(RowEditEvent event) {
    }

    public ChartModel getBarModel() {
        return barModel;
    }


 private TreeNode root;
     
    
    
       @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Root", null);

        List<Service> services = serviceService.getAll();

        for (Service service : services) {
            TreeNode serviceNode = new DefaultTreeNode(service.getNom(), root);

            List<Employe> employees = employeService.getEmployeesByService(service);

            for (Employe employee : employees) {
                if (employee.getEmploye() == null) {
                    TreeNode employeeNode = new DefaultTreeNode(employee.getNom() + " " + employee.getPrenom(), serviceNode);

                    List<Employe> managedEmployees = employeService.getManagedEmployees(employee);

                    for (Employe managedEmployee : managedEmployees) {
                        new DefaultTreeNode(managedEmployee.getNom() + " " + managedEmployee.getPrenom(), employeeNode);
                    }
                }
            }
        }
    }
    public TreeNode getRoot() {
        return root;
    }
}
