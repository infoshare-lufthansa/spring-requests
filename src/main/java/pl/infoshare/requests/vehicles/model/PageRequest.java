package pl.infoshare.requests.vehicles.model;

import lombok.Value;

@Value
public class PageRequest {
    Long page = 0L;
    Long pageSize = Long.MAX_VALUE;
}
