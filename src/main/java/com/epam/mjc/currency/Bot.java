package com.epam.mjc.currency;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Bot extends TelegramLongPollingBot {

  @Override
  @SneakyThrows
  public void onUpdateReceived(Update update) {
    if (update.hasMessage()) {
      handleMessage(update.getMessage());
    }
  }

  @SneakyThrows
  private void handleMessage(Message message) {
    if (message.hasText() && message.hasEntities()) {
      Optional<MessageEntity> commandEntity = message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
      if (commandEntity.isPresent()) {
        String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime date = LocalDateTime.now();

        switch (command) {
          case "/start":
            execute(SendMessage.builder()
                    .text("Hello, my name is BestCurrencyBot!" +  "\n"
                            + "Write \"/kz\" command to get a link to check an actual currency of KZ." + "\n"
                            + "Write \"/usd\" command to get a link to check an actual currency of USD." + "\n"
                            + "Write \"/eur\" command to get a link to check an actual currency of EURO.")
                    .chatId(message.getChatId().toString())
                    .build());
            return;

          case "/kz":
            String kzUrl = "https://bhom.ru/currencies/kzt/?sb=yes&startdate=" + dtf.format(date.plusDays(-10)) + "&enddate=" + dtf.format(date);
            String text = "Get the link";

            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List < InlineKeyboardButton > rowInline = new ArrayList < > ();
            rowInline.add(InlineKeyboardButton.builder().text(text).url(kzUrl).build());
            rowsInline.add(rowInline);
            markupInline.setKeyboard(rowsInline);
            message.setReplyMarkup(markupInline);

            execute(SendMessage.builder()
                    .text("click a button to get information about actual currency of KZ.")
                    .chatId(message.getChatId().toString())
                    .replyMarkup(InlineKeyboardMarkup.builder().keyboard(rowsInline).build())
                    .build());
            return;

          case "/usd":
            String usdUrl = "https://bhom.ru/currencies/usd/?sb=yes&startdate=" + dtf.format(date.plusDays(-10)) + "&enddate=" + dtf.format(date);
            String text1 = "Get the link";

            InlineKeyboardMarkup markupInlineUsd = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInlineUsd = new ArrayList<>();
            List < InlineKeyboardButton > rowInlineUsd = new ArrayList < > ();
            rowInlineUsd.add(InlineKeyboardButton.builder().text(text1).url(usdUrl).build());
            rowsInlineUsd.add(rowInlineUsd);
            markupInlineUsd.setKeyboard(rowsInlineUsd);
            message.setReplyMarkup(markupInlineUsd);

            execute(SendMessage.builder()
                    .text("click a button to get information about actual currency of USD.")
                    .chatId(message.getChatId().toString())
                    .replyMarkup(InlineKeyboardMarkup.builder().keyboard(rowsInlineUsd).build())
                    .build());
            return;

          case "/eur":
            String url = "https://bhom.ru/currencies/eur/?sb=yes&startdate=" + dtf.format(date.plusDays(-10)) + "&enddate=" + dtf.format(date);
            String text2 = "Get the link";

            InlineKeyboardMarkup markupInlineEur = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInlineEur = new ArrayList<>();
            List < InlineKeyboardButton > rowInlineEur = new ArrayList < > ();
            rowInlineEur.add(InlineKeyboardButton.builder().text(text2).url(url).build());
            rowsInlineEur.add(rowInlineEur);
            markupInlineEur.setKeyboard(rowsInlineEur);
            message.setReplyMarkup(markupInlineEur);

            execute(SendMessage.builder()
                    .text("click a button to get information about actual currency of EUR.")
                    .chatId(message.getChatId().toString())
                    .replyMarkup(InlineKeyboardMarkup.builder().keyboard(rowsInlineEur).build())
                    .build());
            return;
        }
      }
    }
  }


  @Override
  public String getBotUsername() {
    return "my12Currency_bot";
  }

  @Override
  public String getBotToken() {
    return "1958499949:AAExm7XTf4xcFQwkkXLR_nD9X6K84Njch9o";
  }

  @SneakyThrows
  public static void main(String[] args) {
    Bot bot = new Bot();
    TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
    telegramBotsApi.registerBot(bot);
  }
}
