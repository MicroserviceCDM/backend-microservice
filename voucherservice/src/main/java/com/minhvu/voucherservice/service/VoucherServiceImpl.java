package com.minhvu.voucherservice.service;

import com.minhvu.voucherservice.dto.CreateVoucherRequest;
import com.minhvu.voucherservice.model.Voucher;
import com.minhvu.voucherservice.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService{
    private final VoucherRepository voucherRepository;

    @Override
    public Voucher getVoucherByCode(String code) {
        return voucherRepository.findByCode(code);
    }

    @Override
    public List<Voucher> getVoucherByCodeContaining(String name) {
        return voucherRepository.findByCodeContaining(name);
    }

    @Override
    public BigDecimal getVoucherValueByCode(String voucherCode) {
        Voucher voucher = voucherRepository.findByCode(voucherCode);
        if(voucher!=null){
            return voucher.getDiscount();
        }
        else {
            return null;
        }
    }

    public void useVoucher(Voucher voucher) {
        voucherRepository.save(voucher);
    }

    @Override
    public Optional<Voucher> getVoucherById(String id) {
        return voucherRepository.findById(id);
    }

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    @Override
    public Voucher updateVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public void deleteVoucher(Voucher voucher) {
        voucherRepository.delete(voucher);
    }

    @Override
    public Voucher createVoucher(CreateVoucherRequest voucher) {
        Voucher voucher1 = Voucher.builder()
                .title(voucher.getTitle())
                .code(voucher.getCode())
                .discount(voucher.getDiscount())
                .expirationDate(voucher.getExpirationDate())
                .description(voucher.getDescription())
                .build();
        return voucherRepository.save(voucher1);
    }

}
