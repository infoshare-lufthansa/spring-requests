package pl.infoshare.requests.vehicles.model;

import lombok.Value;

import java.util.Objects;

@Value
public class PageRequest {
    Long page;
    Long pageSize;

    public Long getPage() {
        if (Objects.isNull(page)) {
            return 0L;
        }

        return page;
    }

    public Long getPageSize() {
        if (Objects.isNull(pageSize)) {
            return Long.MAX_VALUE;
        }
        return pageSize;
    }
}
