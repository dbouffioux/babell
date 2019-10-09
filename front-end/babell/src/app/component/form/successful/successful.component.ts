import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-successful',
  templateUrl: './successful.component.html',
  styleUrls: ['./successful.component.scss']
})
export class SuccessfulComponent implements OnInit {

  @Input() public success: string;
  constructor() { }

  ngOnInit() {
  }

}
