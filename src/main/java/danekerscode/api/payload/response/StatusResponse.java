package danekerscode.api.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusResponse {

    private Boolean success;
    private Object data;

    public static StatusResponse success(){
        return new StatusResponse(true,null);
    }

    public static StatusResponse success(Object data){
        return new StatusResponse(true,data);
    }

    public static StatusResponse fail(Object data){
        return new StatusResponse(false,data);
    }
}
