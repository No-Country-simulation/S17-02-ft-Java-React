package com.nocountry.telemedicina.config.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.nocountry.telemedicina.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    
    private final Cloudinary cloudinary;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String uploadAvatar(MultipartFile file, String publicId) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        // las opciones que le paso para cloudinary
        Map<String, Object> params1 = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true,
                "public_id", publicId,
                "transformation",
                new Transformation().width(250).height(250).gravity("faces").crop("fill").radius("max"));
        try {
            // Realiza la carga del archivo a Cloudinary
            Map<String, Object> resp = cloudinary.uploader().upload(file.getBytes(), params1);
            // Separo de toda la respuesta el link donde esta guardada la imagen y la
            // retorno
            String secureUrl = (String) resp.get("secure_url");
            return secureUrl;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @SuppressWarnings("rawtypes")
    public Map delete(String publicId) {
        try {
            Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            return result;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @SuppressWarnings({ "unchecked" })
    public String uploadPdf(MultipartFile file, String publicId) {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("File is empty");
            }

            // Opciones específicas para la carga de PDFs y documentos
            Map<String, Object> params = ObjectUtils.asMap(
                    "use_filename", true,
                    "unique_filename", false,
                    "overwrite", true,
                    "resource_type", "raw", // Se especifica que el tipo de recurso es "raw"
                    "public_id", publicId);

            // Realiza la carga del archivo a Cloudinary
            Map<String, Object> response = cloudinary.uploader().upload(file.getBytes(), params);

            // Separa de toda la respuesta el link donde está guardado el archivo y lo
            // retorna
            String secureUrl = (String) response.get("secure_url");
            return secureUrl;

        } catch (IOException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

}
