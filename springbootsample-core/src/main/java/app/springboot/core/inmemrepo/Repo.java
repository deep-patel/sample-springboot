package app.springboot.core.inmemrepo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by: deep.patel on 22/02/19
 */

@Slf4j
@Component
public class Repo {

    private Map<Integer, String> repo = new HashMap<>();

    public String getValue(int key){
        return repo.get(key);
    }

    @Scheduled(fixedRateString = "${repo.test.refreshInMs}")
    public void refresh() {
        log.info("Initiating {} repository at {}", this.getClass().getCanonicalName(), new Date());
        Date now = new Date();
        for(int i = 1; i<=10; i++)
            repo.put(i, UUID.randomUUID().toString() + " generated at " + now);
    }
}
