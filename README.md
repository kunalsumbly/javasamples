# javasamples

GenerateJee7Annotations reads the faces-config.xml and based on the xml elements, finds the appropriate bean class based on 
managed-bean-class attr and then read the source code from that specific bean and add the @Named annotation as well 
as the bean scope annotation on the managed bean.

sample config xml:-

<!--managed-bean>
  <managed-bean-name>dashboardBean</managed-bean-name>
  <managed-bean-class>be.sofico.web.mgbean.dashboard.DashboardBean</managed-bean-class>
  <managed-bean-scope>view</managed-bean-scope>
 </managed-bean-->
 
 
 Bean code modified:-
 DashboardBean.java 
 
 package be.sofico.web.mgbean.dashboard;

import javax.faces.view.ViewScoped;  --> generated
import javax.inject.Named;  --> generated

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.event.ActionEvent;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardModel;

import be.sofico.web.frmwrk.FacesBean;
import be.sofico.web.frmwrk.mgbean.WebDashboard;
import be.sofico.web.model.dashboard.WebDashboardColumn;
import be.sofico.web.model.dashboard.WebDashboardWidget;
import be.sofico.web.util.SortableDashboardColumn;
import be.sofico.web.ws.MWSException;


@Named("dashboardBean")  --> generated
@ViewScoped --> generated
public class DashboardBean extends FacesBean {
