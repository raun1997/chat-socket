package com.blogspot.sontx.chatsocket.server.event;

import com.blogspot.sontx.chatsocket.lib.bean.AccountInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Occurs when an account info changed.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfoChangedEvent {
    private AccountInfo accountInfo;
}
