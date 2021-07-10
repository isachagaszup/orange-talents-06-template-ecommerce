package br.com.zupacademy.isadora.ecommerce.produto.imagem;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagemRequest {

    @Size(min = 1) @NotNull
    private List<MultipartFile> imagens = new ArrayList<>();

    public ImagemRequest(@Size(min = 1) @NotNull List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
