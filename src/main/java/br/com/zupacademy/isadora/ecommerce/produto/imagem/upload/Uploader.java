package br.com.zupacademy.isadora.ecommerce.produto.imagem.upload;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface Uploader {
    Set<String> envia(List<MultipartFile> files);
}
