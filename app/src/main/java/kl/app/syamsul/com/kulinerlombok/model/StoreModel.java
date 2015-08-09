package kl.app.syamsul.com.kulinerlombok.model;

/**
 * Created by syamsul on 03/08/15.
 */
        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import com.fasterxml.jackson.annotation.JsonProperty;

        import kl.app.syamsul.com.kulinerlombok.helper.TimeFormatter;

public class StoreModel implements Serializable {

    public static final String KEY_RATING_SERVICE = "service";
    public static final String KEY_RATING_COMFORT = "comfortably";
    public static final String KEY_RATING_CLEANNESS = "cleanness";
    public static final String KEY_RATING_TASTE = "taste";
    public static final String KEY_RATING_OVERALL = "overall";

    public static final String KEY_OPERATION_TIME_START = "start";
    public static final String KEY_OPERATION_TIME_END = "end";

    public static final String KEY_LOCATION_LATITUDE = "lat";
    public static final String KEY_LOCATION_LONGITUDE = "lng";

    public static final String KEY_PHOTO_URL = "url";
    public static final String KEY_PHOTO_DESCRIPTION = "description";

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("_id")
    String _id;

    @JsonProperty("price_range")
    String priceRange;

    @JsonProperty("favorite_menu")
    String favoriteMenu;

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

    @JsonProperty("location")
    Map<String,Long> location = new HashMap<>();

    @JsonProperty("sticky")
    boolean sticky;

    /**
     * Rating terdiri dari
     * {cleanness:int,service:int,comfort:int,taste:int,overall:int}
     */
    @JsonProperty("rating")
    Map<String,Integer> rating = new HashMap<>();

    @JsonProperty("comments_count")
    int commentsCount;

    /**
     * Terdiri dari: {start:string,end:string}
     */
    @JsonProperty("operation_time")
    Map<String,String> operationTime = new HashMap<>();

    /**
     * format json foto adalah array berisi objek
     * yang terdiri dari url dan description
     * [
     *  {url:http://photo.kulinerlombok.com/,description:"bla bla bla"},
     *  {url:http://photo.kulinerlombok.com/,description:"bla bla bla"}
     * ]
     */
    @JsonProperty("photos")
    List<Map<String,String>> photos = new ArrayList<>();

    public StoreModel(){}

    public StoreModel(JSONObject o) throws JSONException{

        if(o.has("_id")){
            setId(o.getString("_id"));
        }
        if(o.has("price_range")){
            setId(o.getString("price_range"));
        }
        if(o.has("favorite_menu")){
            setId(o.getString("favorite_menu"));
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
        if(o.has("description")){
            setDescription(o.getString("description"));
        }
        if(o.has("sticky")){
            setSticky(o.getBoolean("sticky"));
        }
        if(o.has("comments_count")){
            setCommentsCount(o.getInt("comments_count"));
        }

        if(o.has("location")){
            try {
                JSONObject location = o.getJSONObject("location");
                setLocation(location);
            } catch (JSONException e){

            }
        }

        if(o.has("operation_time")){
            try {
                JSONObject operationTime = o.getJSONObject("operation_time");
                setOperationTime(operationTime);
            } catch (JSONException e){

            }
        }

        if(o.has("rating")){
            try {
                JSONObject rating = o.getJSONObject("rating");
                setRating(rating);
            } catch (JSONException e){

            }
        }

        if(o.has("photos")){
            try{
                setPhotos(o.getJSONArray("photos"));
            } catch (JSONException e){

            }
        }
    }

    public void setSticky(boolean sticky){
        this.sticky = sticky;
    }

    public void setId(String id){
        this._id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setPhotos(JSONArray p) throws JSONException{
        for (int i=0; i<p.length();i++){

            Map<String,String> photoItem = new HashMap<>();
            JSONObject item = p.getJSONObject(i);

            photoItem.put(KEY_PHOTO_URL,String.valueOf(item.get(KEY_PHOTO_URL)));
            photoItem.put(KEY_PHOTO_DESCRIPTION,String.valueOf(item.get(KEY_PHOTO_DESCRIPTION)));
            photos.add(photoItem);
        }
    }

    public void setPhotos(JSONObject photo) throws JSONException {
        Map<String,String> item = new HashMap<>();
        item.put(KEY_PHOTO_URL,String.valueOf(photo.get(KEY_PHOTO_URL)));
        item.put(KEY_PHOTO_DESCRIPTION,String.valueOf(photo.get(KEY_PHOTO_DESCRIPTION)));
        photos.add(item);
    }

    public void setLocation(JSONObject location) throws JSONException {
        this.location.put(KEY_LOCATION_LATITUDE, (Long) location.get(KEY_LOCATION_LATITUDE));
        this.location.put(KEY_LOCATION_LONGITUDE, (Long) location.get(KEY_LOCATION_LONGITUDE));
    }

    public void setOperationTime(JSONObject operationTime) throws JSONException {
        this.operationTime.put(KEY_OPERATION_TIME_START, String.valueOf(operationTime.get(KEY_OPERATION_TIME_START)));
        this.operationTime.put(KEY_OPERATION_TIME_END, String.valueOf(operationTime.get(KEY_OPERATION_TIME_END)));
    }

    public void setCommentsCount(int num){
        this.commentsCount = num;
    }

    public void setRating(JSONObject rating) throws JSONException {
        this.rating.put(KEY_RATING_CLEANNESS, (Integer) rating.get(KEY_RATING_CLEANNESS));
        this.rating.put(KEY_RATING_COMFORT, (Integer) rating.get(KEY_RATING_COMFORT));
        this.rating.put(KEY_RATING_TASTE, (Integer) rating.get(KEY_RATING_TASTE));
        this.rating.put(KEY_RATING_SERVICE, (Integer) rating.get(KEY_RATING_SERVICE));
        this.rating.put(KEY_RATING_OVERALL, (Integer) rating.get(KEY_RATING_OVERALL));
    }

    public void setWebsite(String website){
        this.website = website;
    }

    public void setTelp(String telp){
        this.telp = telp;
    }

    public List<Map<String,String>> getPhotos() {
        return this.photos;
    }

    public int getCommentsCount(){
        return commentsCount;
    }

    public Map<String,String> getOperationTime(){
        return operationTime;
    }

    public String getId(){
        return _id;
    }

    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }

    public String getAddress(){
        return address;
    }

    public String getDescription(){
        return description;
    }

    public Map<String,Long> getLocation(){
        return location;
    }

    public boolean getSticky(){
        return sticky;
    }

    public Map<String,Integer> getRating(){
        return rating;
    }

    public String getWebsite(){
        return this.website;
    }

    public String getTelp(){
        return this.telp;
    }
}
