package org.acme.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private long id;

    private char[] name;

    private char[] phone;

    private char[] email;

    private char[] address;

    private long age;
}
