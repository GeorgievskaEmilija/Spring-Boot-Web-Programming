package mk.finki.ukim.lab.repository.InMemoryRepository;

import mk.finki.ukim.lab.database.Dataholder;
import mk.finki.ukim.lab.model.Balloon;
import mk.finki.ukim.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBalloonRepository {
    public List<Balloon> findAllBalloons() {
        return Dataholder.balloons;

    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return Dataholder.balloons.stream().filter(b -> b.getDescription().contains(text) ||
                b.getName().contains(text)).collect(Collectors.toList());

    }

    public Optional<Balloon> findById(Long id) {
        return Dataholder.balloons.stream().filter(b -> b.getId()
                .equals(id)).findFirst();
    }

    public void deleteBalloon(Long id) {
        Dataholder.balloons.removeIf(b -> b.getId().equals(id));
    }

    public Optional<Balloon> saveBalloon(String name, String desc, Manufacturer manufacturer) {
        Dataholder.balloons.removeIf(b -> b.getName().equals(name));
        Balloon b = new Balloon(name, desc);
        b.setManufacturer(manufacturer);
        Dataholder.balloons.add(b);
        return Optional.of(b);
    }
}
