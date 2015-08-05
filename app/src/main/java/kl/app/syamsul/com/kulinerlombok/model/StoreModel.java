package kl.app.syamsul.com.kulinerlombok.model;

/**
 * Created by syamsul on 03/08/15.
 */
        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import com.fasterxml.jackson.annotation.JsonProperty;

        import kl.app.syamsul.com.kulinerlombok.helper.TimeFormatter;

public class StoreModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("_id")
    String _id;

    @JsonProperty("telp")
    String telp;

    @JsonProperty("website")
    String website;

    @JsonProperty("category")
    String category;

    @JsonProperty("name")
    String name;

    @JsonProperty("address")
    String address;

    @JsonProperty("description")
    String description;

    @JsonProperty("lattitude")
    double lattitude;

    @JsonProperty("longitude")
    double longitude;

    @JsonProperty("sticky")
    boolean sticky;

    @JsonProperty("rating")
    int rating;

    @JsonProperty("numComments")
    int numComments;

    @JsonProperty("operation_time_start")
    String operation_time_start;

    @JsonProperty("operation_time_end")
    String operation_time_end;

    @JsonProperty("photos")
    List<String> photos = new ArrayList<String>();

    int p;

    public StoreModel(){}

    public StoreModel(JSONObject o) throws JSONException{

        if(o.has("_id")){
            setId(o.getString("_id"));
        }
        if(o.has("name")){
            setName(o.getString("name"));
        }
        if(o.has("category")){
            setCategory(o.getString("category"));
        }
        if(o.has("website")){
            setWebsite(o.getString("website"));
        }
        if(o.has("telp")){
            setTelp(o.getString("telp"));
        }
        if(o.has("address")){
            setAddress(o.getString("address"));
        }
        if(o.has("rating")){
            setRating(o.getInt("rating"));
        }
        if(o.has("description")){
            setDescription(o.getString("description"));
        }
        if(o.has("lattitude")){
            setLattitude(o.getDouble("lattitude"));
        }
        if(o.has("longitude")){
            setLongitude(o.getDouble("longitude"));
        }
        if(o.has("sticky")){
            setSticky(o.getBoolean("sticky"));
        }
        if(o.has("operationTimeStart")){
            setOperationTimeStart(o.getString("operationTimeStart"));
        }
        if(o.has("operationTimeEnd")){
            setOperationTimeEnd(o.getString("operationTimeEnd"));
        }
        if(o.has("numComments")){
            setNumComments(o.getInt("numComments"));
        }
        if(o.has("photos")){
            try{
                o.getJSONArray("photos");
                setPhotos(o.getJSONArray("photos"));
            } catch (JSONException e){

            }
        }
    }

    public void setP(int l){
        this.p = l;
    }

    public int getP(){
        return this.p;
    }

    public void setPhotos(JSONArray photos) throws JSONException{
        if(photos.length() > 0){
            for (int i = 0; i < photos.length(); i++) {
                this.photos.add(photos.getString(i));
            }
        }
    }

    public void setPhotos(String[] photos){
        if(photos.length > 0){
            for (int i = 0; i < photos.length; i++) {
                this.photos.add(photos[i]);
            }
        }
    }

    public void setPhotos(String photo){
        this.photos.add(photo);
    }

    public List<String> getPhotos() {
        return this.photos;
    }

    public int getNumComments(){
        return numComments;
    }

    public void setNumComments(int num){
        this.numComments = num;
    }

    public void setOperationTimeStart(String op_start){
        this.operation_time_start = TimeFormatter.twoDigitFormat(op_start);
    }

    public String getOperationTimeStart(){
        return this.operation_time_start;
    }

    public void setOperationTimeEnd(String op_end){
        this.operation_time_end = TimeFormatter.twoDigitFormat(op_end);
    }

    public String getOperationTimeEnd(){
        return this.operation_time_end;
    }

    public void setId(String id){
        this._id = id;
    }

    public String getId(){
        return _id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getCategory(){
        return category;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    public String getDescription(){
        return description;
    }

    public void setLattitude(double lat){
        this.lattitude = lat;
    }

    public double getLattitude(){
        return lattitude;
    }

    public void setLongitude(double lon){
        this.longitude = lon;
    }

    public double getLongitude(){
        return longitude;
    }

    public void setSticky(boolean sticky){
        this.sticky = sticky;
    }

    public boolean getSticky(){
        return sticky;
    }

    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public void setWebsite(String website){
        this.website = website;
    }

    public String getWebsite(){
        return this.website;
    }

    public void setTelp(String telp){
        this.telp = telp;
    }

    public String getTelp(){
        return this.telp;
    }
}
