package by.courses.java.streamapi.operation;

import by.courses.java.streamapi.entity.UserBase;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class DefaultStream implements Operation<UserBase> {

    @Override
    public Collection<UserBase> removeWithMaxAge(Collection<UserBase> entities) {
        UserBase max = entities.stream().max(Comparator.comparing(UserBase::getAge)).orElse(null);
        return max != null ? entities.stream()
                .filter(elem -> elem.getAge() < max.getAge())
                .collect(Collectors.toList()) : entities;
    }

    @Override
    public Collection<UserBase> removeAllOlder(Collection<UserBase> entities, int age) {
        return entities.stream().filter(elem -> elem.getAge() <= age).collect(Collectors.toList());
    }

    @Override
    public double getAverageAge(Collection<UserBase> entities) {
        return entities.stream().mapToDouble(UserBase::getAge).average().orElse(0.0);
    }

    @Override
    public UserBase getThirdInCollection(Collection<UserBase> entities) {
        return entities.stream().skip(2).findFirst().orElse(null);
    }

    @Override
    public Collection<UserBase> getTwoUsersStartingFromSecond(Collection<UserBase> entities) {
        return entities.stream().skip(1).limit(2).collect(Collectors.toList());
    }

    @Override
    public boolean isCharacterPresentInAllNames(Collection<UserBase> entities, String character) {
        return entities.stream()
                .allMatch(elem -> elem.getName().toLowerCase()
                                                .contains(character.toLowerCase().subSequence(0, character.length())));
    }

    @Override
    public Collection<UserBase> addValueToAllNames(Collection<UserBase> entities, String value) {
        return entities.stream().map(elem -> UserBase.of(elem.getName()+value, elem.getAge())).collect(Collectors.toList());
    }

    @Override
    public Collection<UserBase> sortByNameThanByAge(Collection<UserBase> entities) {
        return entities.stream()
                .sorted(Comparator.comparing(UserBase::getName).thenComparing(UserBase::getAge))
                .collect(Collectors.toList());
    }
}
