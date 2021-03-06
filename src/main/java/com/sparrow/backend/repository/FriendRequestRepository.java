package com.sparrow.backend.repository;

import com.sparrow.backend.model.FriendRequest;
import com.sparrow.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    List<FriendRequest> findAllBySenderAndStatus(User sender, FriendRequest.Status status);

    List<FriendRequest> findAllByReceiverAndStatus(User receiver, FriendRequest.Status status);

    List<FriendRequest> findBySenderAndReceiverAndStatus(User sender, User receiver, FriendRequest.Status status);

}
