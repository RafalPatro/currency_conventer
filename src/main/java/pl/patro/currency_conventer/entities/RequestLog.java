package pl.patro.currency_conventer.entities;

import javax.persistence.*;

@Entity
@Table(name = "request_logs", schema = "currencies")
public class RequestLog {

    @Column(name = "log_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @Column
    private String url;

    @Column
    private String urlParameters;

    @Column
    private int responseCode;

    public RequestLog(String type, String url, String urlParameters, int responseCode) {
        this.type = type;
        this.url = url;
        this.urlParameters = urlParameters;
        this.responseCode = responseCode;
    }

    public RequestLog(Long id, String type, String url, int responseCode) {
        this.id = id;
        this.type = type;
        this.url = url;
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlParameters() {
        return urlParameters;
    }

    public void setUrlParameters(String urlParameters) {
        this.urlParameters = urlParameters;
    }
}
