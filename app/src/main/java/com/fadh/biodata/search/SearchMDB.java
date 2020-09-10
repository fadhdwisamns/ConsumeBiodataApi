
package com.fadh.biodata.search;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchMDB implements Serializable, Parcelable
{

    @SerializedName("Search")
    @Expose
    private List<Search> search = null;
    @SerializedName("totalResults")
    @Expose
    private String totalResults;
    @SerializedName("Response")
    @Expose
    private String response;
    public final static Creator<SearchMDB> CREATOR = new Creator<SearchMDB>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SearchMDB createFromParcel(Parcel in) {
            return new SearchMDB(in);
        }

        public SearchMDB[] newArray(int size) {
            return (new SearchMDB[size]);
        }

    }
    ;
    private final static long serialVersionUID = -7850064326236294244L;

    protected SearchMDB(Parcel in) {
        in.readList(this.search, (Search.class.getClassLoader()));
        this.totalResults = ((String) in.readValue((String.class.getClassLoader())));
        this.response = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public SearchMDB() {
    }

    /**
     * 
     * @param search
     * @param totalResults
     * @param response
     */
    public SearchMDB(List<Search> search, String totalResults, String response) {
        super();
        this.search = search;
        this.totalResults = totalResults;
        this.response = response;
    }

    public List<Search> getSearch() {
        return search;
    }

    public void setSearch(List<Search> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(search);
        dest.writeValue(totalResults);
        dest.writeValue(response);
    }

    public int describeContents() {
        return  0;
    }

}
