package com.minhvu.voucherservice.service;

import com.minhvu.voucherservice.dto.CreateVoucherRequest;
import com.minhvu.voucherservice.model.Voucher;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface VoucherService {
    Voucher getVoucherByCode(String code);
    List<Voucher> getVoucherByCodeContaining(String name);
    BigDecimal getVoucherValueByCode(String voucherCode);
    void useVoucher(Voucher voucher);
    Optional<Voucher> getVoucherById(String id);
    List<Voucher> getAllVouchers();
    Voucher updateVoucher(Voucher voucher);
    void deleteVoucher(Voucher voucher);
    Voucher createVoucher(CreateVoucherRequest voucher);
}
