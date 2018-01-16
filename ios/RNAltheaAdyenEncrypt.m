#if __has_include(<React/RCTConvert.h>)
#import <React/RCTConvert.h>
#import <React/RCTLog.h>
#else
#import "RCTConvert.h"
#import "RCTLog.h"
#endif

#import "RNAltheaAdyenEncrypt.h"
#import "AdyenCSE/AdyenCSE.h"

static NSString *_publicKey = nil;

@implementation RNAltheaAdyenEncrypt

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

- (id)initWithLaunchOptions:(NSDictionary *)launchOptions key:(NSString *)key
{
    _publicKey = key;
    
    return self;
}

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(encrypt:(NSDictionary *)params
                  callback:(RCTResponseSenderBlock)successCallback) {
  
    ADYCard *card = [ADYCard new];
    card.generationtime = [NSDate new];

    NSString *holderName = [RCTConvert NSString:params[@"card_holder_name"]];
    NSString *cvc = [RCTConvert NSString:params[@"cvc"]];
    NSString *expiryMonth = [RCTConvert NSString:params[@"expiry_month"]];
    NSString *expiryYear = [RCTConvert NSString:params[@"expiry_year"]];
    NSString *number = [RCTConvert NSString:params[@"number"]];

    // set card holder name
    if (holderName) {
        card.holderName = holderName;
    }

    // set card cvc
    if (cvc) {
        card.cvc = cvc;
    }

    // set card expiry month
    if (expiryMonth) {
        card.expiryMonth = expiryMonth;
    }

    // set card expiry year
    if (expiryYear) {
        card.expiryYear = expiryYear;
    }

    // set card number
    if (number) {
        card.number = number;
    }

    NSData *cardData = [card encode];
    NSString *encryptedData = [ADYEncrypter encrypt:cardData publicKeyInHex:_publicKey];

    successCallback(@[[NSNull null], encryptedData]);
}

@end
  
