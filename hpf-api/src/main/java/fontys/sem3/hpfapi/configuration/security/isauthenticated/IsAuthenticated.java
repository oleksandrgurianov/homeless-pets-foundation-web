package fontys.sem3.hpfapi.configuration.security.isauthenticated;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface IsAuthenticated {
}
