
package util;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author sabiha
 */
@Named
@RequestScoped
public class NavigationBean implements Serializable{
    public String page(String p){
        
        
        return "/module/"+p+"/"+p+"?faces-redirect=true";
    }
    
}
