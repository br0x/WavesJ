package com.wavesplatform.wavesj.json.deser;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import com.wavesplatform.wavesj.Transaction;
import com.wavesplatform.wavesj.transactions.*;

import java.io.IOException;

public class TransactionTypeResolver extends TypeIdResolverBase {
    @Override
    public String idFromValue(Object o) {
        return String.valueOf(((Transaction) o).getType());
    }

    @Override
    public String idFromValueAndType(Object o, Class<?> aClass) {
        return idFromValue(o);
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) throws IOException {
        int type = Integer.decode(id);
        Class t;
        switch (type) {
            case AliasTransaction.ALIAS:
                t = AliasTransaction.class;
                break;
            case BurnTransactionV1.BURN:
                t = BurnTransactionV1.class;
                break;
            case DataTransaction.DATA:
                t = DataTransaction.class;
                break;
            case IssueTransactionV1.ISSUE:
                t = IssueTransactionV1.class;
                break;
            case LeaseCancelTransaction.LEASE_CANCEL:
                t = LeaseCancelTransaction.class;
                break;
            case LeaseTransaction.LEASE:
                t = LeaseTransaction.class;
                break;
            case MassTransferTransaction.MASS_TRANSFER:
                t = MassTransferTransaction.class;
                break;
            case ReissueTransactionV1.REISSUE:
                t = ReissueTransactionV1.class;
                break;
            case SetScriptTransaction.SET_SCRIPT:
                t = SetScriptTransaction.class;
                break;
            case SponsorTransaction.SPONSOR:
                t = SponsorTransaction.class;
                break;
            case TransferTransactionV1.TRANSFER:
                t = TransferTransactionV1.class;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return context.constructType(t);
    }
}
