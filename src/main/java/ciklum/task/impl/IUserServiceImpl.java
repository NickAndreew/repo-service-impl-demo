package ciklum.task.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import ciklum.task.repo.IUserImplRepository;
import ciklum.task.src.IUserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class IUserServiceImpl implements IUserService {

    @Autowired
    private IUserImplRepository repository;

    public List<IUserImpl> findAll() {
        List<IUserImpl> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    public List<IUserImpl> findById(String id){
        List<IUserImpl> usersById = new ArrayList<>();
        usersById.add(repository.findById(id).orElse(null));
        return usersById;
    }

    public IUserImpl save(Object user) {
        try {
            repository.save((IUserImpl) user);
        } catch (Exception e){
            e.printStackTrace();
        }
        return (IUserImpl) user;
    }

    public List<IUserImpl> saveAll(Iterable newUsers) {
        List<IUserImpl> users = new ArrayList<>();
        try {
            newUsers.forEach(o -> users.add((IUserImpl) o));
            repository.saveAll(newUsers);
        } catch(Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public IUserImpl delete(String id) {
        List<IUserImpl> list = this.findById(id);
        IUserImpl user = list.get(0);
        if(user!=null) {
            repository.delete(user);
        }
        return user;
    }

    public Map<String, List<IUserImpl>> findAllGroupByGroupId(){
        Map<String, List<IUserImpl>> userAllUsersByGroupIdMap = new HashMap<>();
        Set<String> groupsSet = new HashSet<>();

        repository.findAll().forEach(iUser -> groupsSet.add(iUser.getGroupId()));

        groupsSet.forEach(groupId ->
            userAllUsersByGroupIdMap.put(
                groupId,
                this.findAll()
                    .stream()
                    .filter(iUser -> groupId.equals(iUser.getGroupId()))
                    .collect(Collectors.toList())
            )
        );
        return userAllUsersByGroupIdMap;
    }
}

