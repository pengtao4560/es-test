package service;

import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class MessageServiceImpl2 implements MessageService2 {
    @Override
    // @Transactional
    public String getMessage() {
        return "hello Annotation";
    }
}
