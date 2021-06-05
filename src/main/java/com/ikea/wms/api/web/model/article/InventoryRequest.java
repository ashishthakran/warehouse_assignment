package com.ikea.wms.api.web.model.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Aashish Thakran
 * @version 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class InventoryRequest {

    @JsonProperty("inventory")
    private List<Article> articles;
}
