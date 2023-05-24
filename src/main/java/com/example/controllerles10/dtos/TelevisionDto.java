package com.example.controllerles10.dtos;
import com.example.controllerles10.model.RemoteController;
import com.example.controllerles10.model.Television;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TelevisionDto {
    public Long id;
    @NotBlank
    public String brand;
    @NotBlank
    public String type;
    @Min(value = 25, message = "The price must be at least €25,-")
    public double price;
    @NotNull
    public int screenSize;
    public RemoteController remoteController;

    public TelevisionDto fromTelevision(Television television) {

        TelevisionDto dto = new TelevisionDto();

        dto.id = television.getId();
        dto.brand = television.getBrand();
        dto.price = television.getPrice();
        dto.type = television.getType();
        dto.screenSize = television.getScreenSize();


        return dto;
    }
}
