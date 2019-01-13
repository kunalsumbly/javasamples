# javasamples

GenerateJee7Annotations reads the faces-config.xml and based on the xml elements, finds the appropriate bean class based on 
managed-bean-class attr and then read the source code from that specific bean and add the @Named annotation as well 
as the bean scope annotation on the managed bean.

sample config xml:-

&lt;managed-bean&gt; <br/>
  &lt;managed-bean-name&gt;dashboardBean&lt;/managed-bean-name&gt; <br/>
  &lt;managed-bean-class&gt;be.sofico.web.mgbean.dashboard.DashboardBean&lt;/managed-bean-class&gt; <br/>
  &lt;managed-bean-scope&gt;view&lt;/managed-bean-scope&gt; <br/>
 &lt;/managed-bean&gt; <br/>

 
 Bean code modified:- <br/>
 DashboardBean.java <br/>
 
 package be.sofico.web.mgbean.dashboard; <br/>

import javax.faces.view.ViewScoped;  --> generated <br/>
import javax.inject.Named;  --> generated <br/>

import java.util.ArrayList; <br/>
import java.util.HashMap; <br/>

import javax.faces.event.ActionEvent; <br/>

import org.primefaces.event.CloseEvent; <br/>
import org.primefaces.event.DashboardReorderEvent; <br/>
import org.primefaces.event.ToggleEvent; <br/>
import org.primefaces.model.DashboardColumn; <br/>
import org.primefaces.model.DashboardModel; <br/>
import org.primefaces.model.DefaultDashboardModel; <br/>

import be.sofico.web.frmwrk.FacesBean; <br/>
import be.sofico.web.frmwrk.mgbean.WebDashboard; <br/>
import be.sofico.web.model.dashboard.WebDashboardColumn; <br/>
import be.sofico.web.model.dashboard.WebDashboardWidget; <br/>
import be.sofico.web.util.SortableDashboardColumn; <br/>
import be.sofico.web.ws.MWSException;<br/>


@Named("dashboardBean")  --> generated <br/>
@ViewScoped --> generated <br/>
public class DashboardBean extends FacesBean { <br/>
