package service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
public class MessageServiceImpl2 implements MessageService2 {
    @Override
    @Transactional
    public String getMessage() {
        return "hello Annotation";
    }
}
