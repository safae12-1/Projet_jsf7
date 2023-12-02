/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;
import ma.projet.service.EmployeService;
import ma.projet.service.ServiceService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Safae
 */
@ManagedBean(name = "employeBean")
public class EmployeBean {

    private Employe employe;
    private Employe selectedChef;
    private Service service;
    private List<Employe> employes;
    private List<Employe> chefs;

    private EmployeService employeService;
    private ServiceService serviceService;
    private static ChartModel barModel;

    public EmployeBean() {
        employe = new Employe();
        selectedChef = new Employe();
        employeService = new EmployeService();
        serviceService = new ServiceService();

    }

    public List<Employe> getEmployes() {
        if (employes == null) {
            employes = employeService.getAll();
        }

        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public EmployeService getEmployeService() {
        return employeService;
    }

    public void setEmployeService(EmployeService employeService) {
        this.employeService = employeService;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Employe getSelectedChef() {
        return selectedChef;
    }

    public void setSelectedChef(Employe selectedChef) {
        this.selectedChef = selectedChef;
    }

    public String onCreateAction() {
        employe.setEmploye(selectedChef);
        employe.setIsChef(false);
        employeService.create(employe);
        employe = new Employe();
        selectedChef = new Employe();
        return null;

    }

    public String onDeleteAction() {

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

    public void load() {
        System.out.println(service.getNom());
        service = serviceService.getById(service.getId());
        getEmployes();
    }

    public void onEdit(RowEditEvent event) {
        employe = (Employe) event.getObject();
        Service service = serviceService.getById(this.employe.getService().getId());
        employe.setService(service);
        employe.getService().setNom(service.getNom());
        employeService.update(employe);
    }

    public void onCancel(RowEditEvent event) {
    }

    public ChartModel getBarModel() {
        return barModel;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ServiceService getServiceService() {
        return serviceService;
    }

    public void setServiceService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

}
