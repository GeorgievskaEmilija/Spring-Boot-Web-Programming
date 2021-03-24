package mk.finki.ukim.lab.service.implementations;

import mk.finki.ukim.lab.model.Balloon;
import mk.finki.ukim.lab.model.Manufacturer;
import mk.finki.ukim.lab.repository.BalloonRepository;
import mk.finki.ukim.lab.repository.ManufacturerRepository;
import mk.finki.ukim.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BalloonServiceImplementation implements BalloonService {
    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;

    public BalloonServiceImplementation(BalloonRepository balloonRepository, ManufacturerRepository manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String name) {
        return balloonRepository.findByNameOrDescription(name, name);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return balloonRepository.findById(id);
    }

    @Override
    public Optional<Balloon> save(String name, String description, Long manufacturerId) {
        Manufacturer manufacturer=this.manufacturerRepository.findById(manufacturerId).get();
        return Optional.of(balloonRepository.save(new Balloon(name, description, manufacturer)));
    }

    @Override
    public void delete(Long id) {
        balloonRepository.deleteById(id);
    }
}
