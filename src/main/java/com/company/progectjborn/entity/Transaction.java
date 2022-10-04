package com.company.progectjborn.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "TRANSACTION_", indexes = {
        @Index(name = "IDX_TRANSACTION__FROM_ACC_ID", columnList = "FROM_ACC_ID_ID"),
        @Index(name = "IDX_TRANSACTION__TO_ACC_ID", columnList = "TO_ACC_ID_ID")
})
@Entity(name = "Transaction_")
public class Transaction {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "CREATE_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createDate;

    @Column(name = "TRANSFER_AMOUNT", nullable = false)
    @NotNull
    private Long transferAmount;

    @JoinColumn(name = "FROM_ACC_ID_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinFetch(value = JoinFetchType.INNER)
    private BankAccount fromAccId;

    @JoinColumn(name = "TO_ACC_ID_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinFetch(value = JoinFetchType.INNER)
    private BankAccount toAccId;

    @JoinTable(name = "TRANSACTION_TYPE_LINK",
            joinColumns = @JoinColumn(name = "TRANSACTION_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Type> type;

    public BankAccount getToAccId() {
        return toAccId;
    }

    public void setToAccId(BankAccount toAccId) {
        this.toAccId = toAccId;
    }

    public BankAccount getFromAccId() {
        return fromAccId;
    }

    public void setFromAccId(BankAccount fromAccId) {
        this.fromAccId = fromAccId;
    }

    public List<Type> getType() {
        return type;
    }

    public void setType(List<Type> type) {
        this.type = type;
    }

    public Long getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Long transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}