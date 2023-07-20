package element.service.impl;

import element.entity.Element;
import element.repository.QueryElementRepository;
import element.service.QueryElementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class QueryElementImpl implements QueryElementService {
    @Autowired
    private QueryElementRepository queryElementRepository;

    @Override
    @Transactional(readOnly = true)
    public Element findElementById(String id) {
        return queryElementRepository.findById(id).orElse(null);
    }
}
