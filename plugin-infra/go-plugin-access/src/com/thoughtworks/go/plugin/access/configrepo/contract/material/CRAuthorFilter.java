package com.thoughtworks.go.plugin.access.configrepo.contract.material;

import com.thoughtworks.go.plugin.access.configrepo.ErrorCollection;
import com.thoughtworks.go.plugin.access.configrepo.contract.CRBase;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CRAuthorFilter extends CRBase {
    private List<String> ignore  = new ArrayList<String>();
    // room for whitelist

    public CRAuthorFilter(List<String> ignore) {
        this.ignore = ignore;
    }

    @Override
    public void getErrors(ErrorCollection errors, String parentLocation) {

    }

    @Override
    public String getLocation(String parent) {
        String myLocation = getLocation() == null ? parent : getLocation();
        return String.format("%s; Filter",myLocation);
    }

    public List<String> getIgnore() {
        return ignore;
    }

    public void setIgnore(List<String> ignore) {
        this.ignore = ignore;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        CRAuthorFilter that = (CRAuthorFilter)o;
        if(that == null)
            return  false;

        if (ignore != null ? !CollectionUtils.isEqualCollection(this.ignore, that.ignore) : that.ignore != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 32;
        result = 31 * result + (ignore != null ? ignore.size() : 0);
        return result;
    }

}
