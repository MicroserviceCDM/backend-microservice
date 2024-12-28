package io.github.cdm.voucherservice.repository;

import io.github.cdm.voucherservice.model.Voucher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends MongoRepository<Voucher, String> {

    Voucher findByCode(String code);
    Voucher getById(String id);
    List<Voucher> findByCodeContaining(String name);
}

