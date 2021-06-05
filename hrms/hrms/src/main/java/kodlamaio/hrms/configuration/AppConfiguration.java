package kodlamaio.hrms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import kodlamaio.hrms.core.utilities.helpers.ImageUploadService;
import kodlamaio.hrms.core.utilities.helpers.cloudinary.CloudinaryImageManager;

@Configuration
public class AppConfiguration {

	@Bean
    public Cloudinary cloudinaryService(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "hrmsimagerepository",
                "api_key", "715887566621648",
                "api_secret", "oyvriACmSTpNERYbChnuDvaoLzI"));
    }

    @Bean
    public ImageUploadService imageService(){
        return new CloudinaryImageManager(cloudinaryService());
    }
}
